package com.rust.scala.primitiveakka

import scala.actors.Actor

case class Register(username: String, password: String)

case class Login(username: String, password: String)

/**
  * 向akka发送样例类
  *
  * @author Rust
  */
class ActorDemo02 extends Actor {
  override def act(): Unit = {
    while (true) {
      receive {
        case Login(username, password) => println("Login " + username + " 密码 " + password)
        case Register(username, password) => println("Register " + username + " 密码 " + password)
      }
    }
  }
}

object ActorDemo02 {
  def main(args: Array[String]): Unit = {
    val ac2 = new ActorDemo02
    ac2.start()
    ac2 ! Login("Johnson", "qaz123")
    ac2 ! Register("Greg", "jkloiu")

  }
}