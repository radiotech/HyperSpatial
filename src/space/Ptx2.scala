package space

object Ptx2 {
  def apply(p1: Pt, p2: Pt) = new Ptx2(p1,p2,p1.D);
}

class Ptx2(val p1: Pt, val p2: Pt, override val D: Int) extends Real {
  
  
  def cView(f: Function[Option[Pt],Option[Pt]]): List[space.Real] = {val ps = (f(Some(p1)),f(Some(p2))); if(ps._1==None&&ps._2==None) Nil else if(ps._1!=None&&ps._2!=None) List(Ptx2(ps._1.get,ps._2.get)) else {println("Line half visible!");Nil}}
  def draw(g: java.awt.Graphics, f: Function[Pt,(Int, Int, Boolean)]) {val ps = (f(p1),f(p2)); if(ps._1._3||ps._2._3) g.drawLine(ps._1._1, ps._1._2, ps._2._1, ps._2._2)}
  
  /*
  def dot(p: Pt): Double = Range(0,Math.min(p.D,D),1).map {i=>p(i)*sub(i)}.sum
  def addFrac(v: Vector[Double], d: Double): Vector[Double] = Range(0,Math.min(v.length,D),1).toVector.map{i=>sub(i)+v(i)/d}
  def print = println("x:"+x+"  y:"+y+"  z:"+z+"  w:"+w)
  def mag = Math.sqrt(sub.foldRight(0d)((x,t)=>x*x+t))
  def disTo(p: Pt) = Math.sqrt(Range(0,Math.min(p.D,D),1).foldRight(0d)((i,t)=>(p(i)-sub(i))*(p(i)-sub(i))+t))
  def avg(p: Pt) = Pt(Range(0,Math.min(p.D,D),1).map(i=>(p(i)+sub(i))/2).toVector)
  def view(fov: Double) = {
    val scale = fov/(sub.last+fov)
    Pt(sub.map(_*scale))
  }
  
  override def equals(that: Any): Boolean = {
    that match {
      case x: Pt => x.sub == sub;
      case _ => false
    }
  }
  */
}