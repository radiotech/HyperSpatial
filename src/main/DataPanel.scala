package main
import javax.swing._
import java.awt.{Color,Canvas,Dimension,Graphics}
import java.awt.event._
import java.awt.image.{BufferedImage,DataBufferInt}
import java.awt.image.BufferStrategy

class DataPanel(var w: Int, var h: Int) extends Canvas {
  setFocusable(true)
  requestFocus
  requestFocusInWindow
  setPreferredSize(new Dimension(w, h))
  
  
  this.addKeyListener(new KeyAdapter{
    override def keyPressed(e: KeyEvent) {Main.Keys = Main.Keys + (e.getKeyChar -> 1)}
    override def keyReleased(e: KeyEvent) {Main.Keys = Main.Keys + (e.getKeyChar -> 0)}
  })
  
  
  var img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
  var pixels = Array[Int]()
  
  
  def getGraphics(w: Int, h: Int) = {
    if(w != this.w || h != this.h){
      img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
      pixels = img.getRaster().getDataBuffer().asInstanceOf[DataBufferInt].getData()
      this.w = w;
      this.h = h;
    }
    img.getGraphics
  }
  
  def draw(img: BufferedImage) {
    val bs = getBufferStrategy
    if(bs == null){
      createBufferStrategy(3)
      requestFocus
      return
    }
    
    val g = bs.getDrawGraphics
    
    g.drawImage(img, 0, 0, w, h, null)
    
    g.dispose
    bs.show
  }
  
  
  
  
  def render() {
    
    
    /*
    val dx = g.getClipBounds.width.toFloat  / view.w
    val dy = g.getClipBounds.height.toFloat / view.h
    
    val t0 = System.nanoTime()
    
    view.pixels.zipWithIndex.foreach(x => x._1.zipWithIndex.foreach(y => {
      val x1 = (x._2 * dx)
      val y1 = (y._2 * dy)
      g.setColor(y._1)
      g.fillRect(x1.toInt, y1.toInt, (x1+dx).toInt, (y1+dy).toInt)
    }) )
    /*
    for {
      x <- 0 until view.w
      y <- 0 until view.h
      x1 = (x * dx)
      y1 = (y * dy)
    } {
      view.pixels(x)(y) match {
        case c: Color => g.setColor(c)
        case _ => g.setColor(Color.WHITE)
      }
      g.fillRect(x1.toInt, y1.toInt, (x1+dx).toInt, (x1+dy).toInt)
    }
    */
    
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0)/10000000.0 + "units")
    */
  }
}