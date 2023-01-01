package com.rust.scala.akka.http

import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import akka.util.ByteString
import akka.actor.{Actor, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.concurrent.duration.DurationInt
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.language.postfixOps

/**
 * 在preStart中先发送请求
 * receive函数中直接接受http响应
 * @author tak
 */
class HttpPreStartActor extends Actor {
//    import context.dispatcher

  implicit val system = context.system
    // 需要用到 actor materializer 来处理流
    implicit val materializer = ActorMaterializer

    // 重写 preStart 方法
    override def preStart(): Unit = {
      // 在 actor 启动时发送 HTTP 请求
      val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "https://akka.io"))

      // 将响应管道到自己的 actor 中
      responseFuture.pipeTo(self)
    }

    def receive = {
      case response: HttpResponse =>
          response.entity.toStrict(5 seconds).map(_.data.utf8String)
          .foreach(println)
      // 处理 HTTP 响应，并执行你的业务逻辑
    }
  }



object HttpPreStartActor{
  def main(args: Array[String]): Unit = {

    // 创建 actor system 和 materializer
    implicit val system = ActorSystem("client-system")
    implicit val materializer = ActorMaterializer

    // 创建自定义 actor
    val actor = system.actorOf(Props[HttpPreStartActor], "my-actor")

    actor ! "start"
    // 关闭 actor system
//    system.terminate()

    //    actorSystem.scheduler.scheduleOnce(5.seconds, httpActor, "terminate")
//    actorSystem.terminate()

  }
}



