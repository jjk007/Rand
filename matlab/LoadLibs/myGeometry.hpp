#ifndef MYGEOMETRY_HPP
#define MYGEOMETRY_HPP

class Point {
public:
  double x_cord;
  double y_cord;
  Point() {
    this->x_cord = 0.0;
    this->y_cord = 0.0;
  }
  Point(double x, double y) {
    this->x_cord = x;
    this->y_cord = y;
  }
};

class Line {
public:
  Point start;
  Point end;
  double getSlope();
  double getInclineAngle();
  double getInclineAngle(double);
  Line getLineWithRespectTo(Point, double);
  Line getLineWithRespectTo(double, double, int, char);
  Line reverse(); // Reverse the start and end points of the line.
  Line() {
    this->start.x_cord = 0.0;
    this->start.y_cord = 0.0;
    this->end.x_cord = 0.0;
    this->end.y_cord = 0.0;
  }
  Line(Point a, Point b) {
    this->start = a;
    this->end = b;
  }
};

bool compareLine(Line ,Line);

#endif /* MYGEOMETRY_HPP */
