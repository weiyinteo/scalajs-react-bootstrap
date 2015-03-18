package com.acework.js.utils


import scala.reflect.macros.blackbox

import scala.language.experimental.macros

trait Mappable[T] {
  def toMap(t: T): Map[String, Any]

  def fromMap(m: Map[String, Any]): T
}

/**
 * Created by weiyin on 18/03/15.
 */
object Mappable {
  implicit def materializeMappable[T]: Mappable[T] = macro materializeMappableImpl[T]

  def materializeMappableImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Mappable[T]] = {
    import c.universe._
    val tpe = weakTypeOf[T]
    val companion: Symbol = tpe.typeSymbol.companion

    // get fields from primary constructor

    val fields = tpe.decls.collectFirst {
      case m: MethodSymbol if m.isPrimaryConstructor => m
    }.get.paramLists.head

    val (toMapParams, fromMapParams) = fields.map { field =>
      val name = field.asTerm.name
      val decoded = name.decodedName.toString
      val returnType = tpe.decl(name).typeSignature

      (q"$decoded -> t.$name", q"map($decoded).asInstanceOf[$returnType]")
    }.unzip

    c.Expr[Mappable[T]] { q"""
      new Mappable[$tpe] {
        def toMap(t: $tpe) = Map(..$toMapParams)
        def fromMap(map: Map[String, Any]) = $companion(..$fromMapParams)
      }
    """
    }
  }

}
