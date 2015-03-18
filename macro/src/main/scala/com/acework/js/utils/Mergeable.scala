package com.acework.js.utils

import scala.reflect.macros.blackbox

/**
 * Created by weiyin on 18/03/15.
 */

trait Common {

  def getFieldNamesAndTypes(c: blackbox.Context)(tpe: c.universe.Type): Iterable[(c.universe.Name, c.universe.Type)] = {
    import c.universe._

    object CaseField {
      def unapply(trmSym: TermSymbol): Option[(Name, Type)] = {
        if (trmSym.isVal && trmSym.isCaseAccessor)
          Some((TermName(trmSym.name.toString.trim), trmSym.typeSignature))
        else
          None
      }
    }

    tpe.decls.collect {
      case CaseField(nme, tpe) =>
        (nme, tpe)
    }
  }
}

trait Mergeable[T] {
  def merge(t: T, map: Map[String, Any]): T
}

object Mergeable {

  implicit def materializeMergeable[T]: Mergeable[T] = macro materializeMergeableImpl[T]

  def materializeMergeableImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Mergeable[T]] = {
    import c.universe._
    val tpe = weakTypeOf[T]
    val companion: Symbol = tpe.typeSymbol.companion

    // get fields from primary constructor
    val fields = tpe.decls.collectFirst {
      case m: MethodSymbol if m.isPrimaryConstructor => m
    }.get.paramLists.head

    val fromMapParams = fields.map { field =>
      val name = field.asTerm.name
      val decoded = name.decodedName.toString
      val returnType = tpe.decl(name).typeSignature
      q"map.getOrElse($decoded, t.$name).asInstanceOf[$returnType]"
    }

    //val params: Iterable[Name] = tpe.decls.collect {
    //  case param if param.isMethod && param.asMethod.isCaseAccessor => param.name
    // }

    c.Expr[Mergeable[T]] { q"""
      new Mergeable[$tpe] {
        def merge(t: $tpe, map: Map[String, Any]) = $companion(..$fromMapParams)
      }
    """
    }
  }
}
