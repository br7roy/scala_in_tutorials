package com.rust.scala.primitiveakka

import scala.actors.Actor

/**
  * 向akka发送普通信息
  *
  * @author Rust
  */
class ActorDemo extends Actor {
  override def act(): Unit = {
    while (true) {
      receive {
        case name: String => println("hello:" + name)
        case money: Int => println("cash:" + money)
      }
    }
  }
}

object ActorDemo01 {
  def main(args: Array[String]): Unit = {
    val actorDemo = new ActorDemo
    actorDemo.start()
    actorDemo ! "tom"
    actorDemo ! 100

  }
}