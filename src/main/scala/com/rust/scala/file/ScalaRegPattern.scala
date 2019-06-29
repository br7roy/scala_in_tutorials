package com.rust.scala.file

import scala.util.matching.Regex

/**
  * 正则表达式
  *
  * @author Rust
  */
object ScalaRegPattern {
  def main(args: Array[String]): Unit = {
    //  使用String类的r方法可以创建一个Regex对象
    val numPattern = "[0-9]+".r
    //  如果正则表达式包含反斜杠或引号，使用原始字符串语法
    /*

      """..."""

    */

    val wsnumPattern = """\s+[0-9]+\s+""".r

    //  findAllIn方法返回遍历所有匹配项的迭代器。可以在for循环中使用它
    for (pattern <- numPattern.findAllIn("99 bottles, 98 bottles"))
      println(pattern)

    //  或者将迭代器转成数组
    val matches = numPattern.findAllIn("99 bottles, 98 bottles").toArray
    //  Array("99","98")

    //  要找到数字的首个匹配项，可以使用findFirstIn。得到一个Option[String]
    val firstMatch = wsnumPattern.findFirstIn("99 bottles, 98 bottles")
    //Some(" 98")

    //    没有方法可以测试某个字符串整个与正则表达式匹配，不过可以添加行首尾的锚定(anchor)
    val anchoredPattern = "^[0-9]+$".r
    val str = "123"
    if (anchoredPattern.findFirstIn(str) != None) {


    }

    //    也可以使用String.matches方法
    //    if(str.matches("[0-9]+"))...


    //--------------
    //  替换首个匹配、所有匹配或某些匹配。对于最后一种情形，提供一个Match => Option[String] 的
    //  如果该函数返回Some(str),那么这个匹配就会被替换成str
    numPattern.replaceFirstIn("99 bottles, 98 bottles", "XX")
    //  "XX bottiles, 98 bottles"
    numPattern.replaceAllIn("99 bottles, 98 bottles", "XX")
    //  "XX bottles, XX bottles"
    numPattern.replaceSomeIn("99 bottles, 98 bottles", m => if (m.matched.toInt % 2 == 0) Some("XXX") else None)
    //  "99 bottles, XX bottles"

    //  用一组参数一次替换某个消息字符串中的占位符$0,$1等，制作一个对下表分组的模式
    //  并将分组映射到序列元素
    val valPattern =
    """\$[0-9]+""".r

    def format(message: String, vars: String*) = valPattern.replaceSomeIn(message, m => vars.lift(m.matched.tail.toInt))

    format("At $1, there was $2 on $0.", "planet 7", "12:30 pm", "a disturbance of the force")
    //  At 12:30 pm, there was a disturbance of the force on planet 7."
    //  这里de Seq[String] 转换成函数。表达式vars.lif(i)的i是合法下标时求值得到Some(vars(i),而在i不是合法下表时求值得到None


    //--------------
    //  正则表达式组

    """
      |
    """.stripMargin

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //分组可以方便的获取正则表达式的子表达式，在想要获取的子表达式两侧加上圆括号，例如
    val multiItemPattern = "([0-9]+)([a-z]+)".r
    for (i <- multiItemPattern.findAllMatchIn("99 bottles, 98 bottles"))
      println(i.group(1)) //  打印99和98


    //------------------
    //  使用提取器，提取匹配项
    val multiItemPattern(num, item) = "99 bottles"
    //  从多个匹配中提取分组内容
    for (multiItemPattern(num, item) <- multiItemPattern.findAllIn("99 bottles, 98 bottles"))
    //  处理num,item
      println(num, item)



  }

}
