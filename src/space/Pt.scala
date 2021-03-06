package space

object Pt {
  def apply(d: Int) = new Pt(d);
  def apply(v: Vector[Double]): Pt = {
    val p = new Pt(v.length)
    p.translate(v)
    p
  }
}

class Pt(override val D: Int) extends Real {
  
  def apply(i: Int) = translation(i);
  
  def cView(f: Function[Option[Pt],Option[Pt]]): List[space.Real] = {val p = f(Some(this)); if(p==None) Nil else List(p.get)}
  def draw(g: java.awt.Graphics, f: Function[Pt,(Int, Int, Boolean)]) {println(f(this));val p = f(this); if(p._3) g.fillOval(p._1, p._2, 3, 3)}
  
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