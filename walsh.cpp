#include <bits/stdc++.h>
using namespace std;
#define CONST 1;
/* O structura ce modeleaza un element din matrice */
struct Point {
    int x_abs;
    int y_ord;

    Point(int _x_abs, int _y_ord)
        : x_abs(_x_abs)
        , y_ord(_y_ord) { }
};

class Task {
 public:
    void solve() {
        read_input();
        print_output();
    }

 private:
    int n, k;
    vector<Point> points;

    void read_input() {
        ifstream fin("walsh.in");
        fin >> n >> k;
        for (int i = 0, x_abs, y_ord; i < k; i++) {
            fin >> x_abs >> y_ord;
            points.push_back(Point(x_abs, y_ord));
        }
        fin.close();
    }
    /* Functia care determina valoarea elementului 
        de pe (search_x,search_y) folosind Divide et Impera */
    int get_walsh(int dim, int res, int search_x_max,
    int search_y_max, int search_x, int search_y) {
        /* Daca se afla in submatricea din stanga jos */

       if (search_x > search_x_max/2 && search_y <= search_y_max/2) {
           /* Cautarea se termina daca dimensiunea este 1 */
           if (dim == 1)
           return res;
           else
           /* Se apeleaza in mod recursiv folosind coordonatele reduse */
           return get_walsh(dim/2, res, search_x_max/2, search_y_max/2,
           search_x-search_x_max/2, search_y);
       }
       /* Daca se afla in submatricea din dreapta-sus */
       if (search_x <= search_x_max/2 && search_y > search_y_max/2) {
           /* Cautarea se termina daca dimensiunea este 1 */
           if (dim == 1)
           return res;
           else
           /* Se apeleaza in mod recursiv folosind coordonatele reduse */
           return get_walsh(dim/2, res, search_x_max/2, search_y_max/2,
           search_x, search_y-search_y_max/2);
       }
       /* Daca se afla in submatricea din stanga-sus */
       if (search_x <= search_x_max/2 && search_y <= search_y_max/2) {
           /* Cautarea se termina daca dimensiunea este 1 */
           if (dim == 1)
           return res;
           else
           /* Se apeleaza in mod recursiv folosind aceleasi coordonate */
           return get_walsh(dim/2, res, search_x_max/2, search_y_max/2,
           search_x, search_y);
         /* Daca se afla in submatricea din dreapta-jos */
       } else {
           /* Cautarea se termina daca dimensiunea este 1 */
           if (dim == 1)
           return res;
           else
           /* Se apeleaza in mod recursiv folosind coordonatele reduse */
           return get_walsh(dim/2, !res, search_x_max/2, search_y_max/2,
           search_x-search_x_max/2, search_y-search_y_max/2);
       }
    }

    void print_output() {
        ofstream fout("walsh.out");
        for (int i = 0; i < k; i++)
        fout << get_walsh(n, 0, n, n, points[i].x_abs, points[i].y_ord) << "\n";
        fout.close();
    }
};

int main(int argc, char* argv[]) {
    auto* task = new (nothrow) Task();
    if (!task) {
        cerr << "Error!\n";
        return -1;
    }
    task->solve();
    delete task;
    return 0;
}
