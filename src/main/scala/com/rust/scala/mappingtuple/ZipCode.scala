package com.rust.scala.mappingtuple

/**
  * 展示拉链操作
  *
  * @author Rust
  */
object ZipCode {
  def main(args: Array[String]): Unit = {


    val keys = Array("zhangsan", "lisi", "wangwu")
    val vals = Array(20, 30, 33)

    val mapping = keys.zip(vals)

    println(mapping.mkString(","))


  }

}
