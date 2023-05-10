package lectures.part3fp

object Options extends App {
  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs
  def unsafeMethod():String = null
  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) //UNSAFE DO NOT USE THIS

  // map flatmap filter
  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(_>10))
  println(myFirstOption.flatMap(x => Option(x*10)))

  // for comprehensions
  val config: Map[String, String] = Map(
    "host" -> "00000000000",
    "port" -> "8080"
    )

  class Connection {
    def connect = "Connected"
    def disconnect = "Disconnected"
  }
  object Connection {
    val random = new scala.util.Random(System.nanoTime())
    def apply(host:String, port:String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  val connectionStatus = connection.map(c => c.connect)
  connectionStatus.foreach(println)

  config.get("host")
    .flatMap(h => config.get("port")
      .flatMap(p => Connection(h, p))
      .map(c => c.connect))
    .foreach(println)

  // for comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

  forConnectionStatus.foreach(println)

}
