package com.rust.scala.akka.http
import akka.actor.TypedActor.context
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._

import scala.concurrent.Future
import scala.util.{Failure, Success}
/**
 * @author tak
 */
object completeDemo {

  implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.executionContext


  def main(args: Array[String]): Unit = {


    for (_ <- 1 to 10 ){

      dojob()
    }

  }

  def dojob(): Unit = {


    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "https://baidu.com"))

    responseFuture
      .onComplete {
        case Success(res) => println(res)
        case Failure(_) => sys.error("something wrong")
      }
    println("ok")
  }
//  TimeUnit.SECONDS.sleep(5)
//  println("end")
  system.terminate()
}
