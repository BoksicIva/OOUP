#include <iostream>
#include <assert.h>
#include <stdlib.h>

/*Dodajte metodu moveShapes koja pomiče oblike zadane prvim argumentom za translacijski pomak određen ostalim argumentima. Ispitajte dodanu funkcionalnost.

Dodajte razred Rhomb. Dodajte jedan objekt tipa Rhomb u listu objekata u main(). Sjetite se, sad moramo promijeniti i drawShapes().

Ovo je domino-efekt (krutost), kojeg ćemo kasnije pokušati zauzdati. Za probu, zaboravite adekvatno promijeniti moveShapes(). 
Isprobajte ponovo. Sad bi moveShapes trebao "puknuti". To je krhkost uzrokovana redundancijom. Ni to ne želimo imati u programu.

Konačno, implementirajte rješenje s predavanja, i komentirajte njegova svojstva.*/

struct Point
{
    int x;
    int y;
};

struct Shape
{
    enum EType
    {
        circle,
        square,
        rhomb
    };
    EType type_;
};

struct Circle
{
    Shape::EType type_;
    double radius_;
    Point center_;
};

struct Square
{
    Shape::EType type_;
    double side_;
    Point center_;
};

struct Rhomb
{
    Shape::EType type_;
    double side_;
    Point center_;
};

void drawSquare(struct Square *)
{
    std::cerr << "in drawSquare\n";
}

void drawCircle(struct Circle *)
{
    std::cerr << "in drawCircle\n";
}

void drawRhomb(struct Rhomb *)
{
    std::cerr << "in drawRhomb\n";
}

void moveSquare(struct Square * s, struct Point *point)
{
    s->center_.x+=point->x;
    s->center_.y+=point->y;
    std::cerr << "in moveSquare\n";
}

void moveCircle(struct Circle * s, struct Point *point)
{
    s->center_.x+=point->x;
    s->center_.y+=point->y;
    std::cerr << "in moveCircle\n";
}
void moveRhomb(struct Rhomb * s,struct Point *point)
{
    s->center_.x+=point->x;
    s->center_.y+=point->y;
    std::cerr << "in moveRhomb\n";
}

void drawShapes(Shape **shapes, int n)
{
    for (int i = 0; i < n; ++i)
    {
        struct Shape *s = shapes[i];
        switch (s->type_)
        {
        case Shape::square:
            drawSquare((struct Square *)s);
            break;
        case Shape::circle:
            drawCircle((struct Circle *)s);
            break;
        case Shape::rhomb:
            drawRhomb((struct Rhomb *)s);
            break;
        default:
            assert(0);
            exit(0);
        }
    }
}

void moveShapes(Shape **shapes, int n,struct Point *point)
{
    for (int i = 0; i < n; ++i)
    {
        struct Shape *s = shapes[i];
        switch (s->type_)
        {
        case Shape::square:
            moveSquare((struct Square *)s, point);
            break;
        case Shape::circle:
            moveCircle((struct Circle *)s, point);
            break;
        case Shape::rhomb:
        moveRhomb((struct Rhomb *)s, point);
        break;
        default:
            assert(0);
            exit(0);
        }
    }
}

int main()
{
    Shape *shapes[4];
    shapes[0] = (Shape *)new Circle;
    shapes[0]->type_ = Shape::circle;
    shapes[1] = (Shape *)new Square;
    shapes[1]->type_ = Shape::square;
    shapes[2] = (Shape *)new Square;
    shapes[2]->type_ = Shape::square;
    shapes[3] = (Shape *)new Circle;
    shapes[3]->type_ = Shape::circle;
    shapes[4] = (Shape *)new Rhomb;
    shapes[4]->type_ = Shape::rhomb;
    struct Point *pointToMove = (Point *)new Point;
    pointToMove->x=5;
    pointToMove->y=6;

    drawShapes(shapes, 5);
    moveShapes(shapes,5,pointToMove);
    return 0;
}