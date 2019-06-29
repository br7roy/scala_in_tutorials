package com.rust.scala.file

import scala.io.Source

/**
  * @author Rust
  */
object Exercise {
  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("/t1.txt")
    val buffered = source.buffered



  }

}
