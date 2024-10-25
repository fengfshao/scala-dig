package com.berry.scalaProgramming

/**
 * scala编程第三版22-28章本地调试
 *
 * @author leonardo
 * @since 2024/10/23
 *
 */
case class Person(name: String, isMale: Boolean, children: Person*)
object Email {
  def apply(user: String, domain: String): String = user + "@" + domain
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split ("@")
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }

  // TODO 抽取器的序列模式
  def unapplySeq(str:String):Option[Seq[Option[(String,String)]]]={
    val parts= str.split(",")
    if(parts.isEmpty) None else Some(parts.map(unapply).toSeq)
  }
}
object Ch22_28 {
  def main(args: Array[String]): Unit = {
    val persons = List.empty[Person]

    // 高级for表达式，相比于高阶函数调用更有可读性
    // 每个for表达式都可以转译为map，flatMap和filter的组合
    val info = for (p <- persons; if !p.isMale; c <- p.children) yield (p.name, c.name)
    // 实际上代码会编译为
    val info2 = persons.filter(p => !p.isMale).flatMap(p => p.children.map(c => (p.name, c.name)))

    // 抽取器，模式匹配中自动调用unapply，如果返回None则匹配失败
    val emailStr: String = Email("John", "epfl.ch")
    val Email(user,domain)="a@b"
    val ops: Option[(String, String)] =Email.unapply("John@epfl.ch")
    "John@epfl.ch" match {
      case Email(u,d)=>
    }
    // 利用正则进行抽取
    val Decimal="(-)?(\\d+)(\\.\\d*)?".r
    val Decimal(s, i, d) = "-1.23"
    val Decimal(s1, i1, d1) = "2"
    println(s,i,d)
    println(s1,i1,d1) // s1,d1 is null
  }
}
