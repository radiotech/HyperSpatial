package space

object Sub {
  def apply(sub: List[Pt]) = new Sub(sub);
}

class Sub(val sub: List[Pt]) {
  def apply(i: Int): Pt = sub(i)
  lazy val subD = sub.length-1;
  lazy val D = sub(0).D;
}