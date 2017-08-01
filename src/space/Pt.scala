package space

object Pt {
  def apply(sub: Vector[Double]) = new Pt(sub)
  def apply(x: Double) = new Pt((x::Nil).toVector)
  def apply(x: Double, y: Double) = new Pt(Vector(x,y))
  def apply(x: Double, y: Double, z: Double) = new Pt(Vector(x,y,z));
  def apply(x: Double, y: Double, z: Double, w: Double) = new Pt(Vector(x,y,z,w))
  def zeros(d: Int) = Pt(Vector.fill(d)(0.0));
}


class Pt(val sub: Vector[Double]) {
  val D = sub.length
  val x = sub(0)
  val y = if(D>1) sub(1) else 0
  val z = if(D>2) sub(2) else 0
  val w = if(D>3) sub(3) else 0
  
  def apply(i: Int) = sub(i);
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
}