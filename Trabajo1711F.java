public class Main {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.punto1();
        arbol.punto2();
        arbol.punto3();
        arbol.punto4();
        arbol.punto5();
        arbol.punto6();
        arbol.punto7();
        arbol.punto8();
        arbol.punto9();
        arbol.punto10();
    }
}

class ArbolBinario {

    static class Nodo {
        int valor;
        Nodo izquierdo, derecho;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    static class NodoReino {
        String nombre;
        NodoReino izquierdo, derecho;

        NodoReino(String nombre) {
            this.nombre = nombre;
        }
    }

    Nodo raiz;
    NodoReino raizReinos;

    // Punto 1
    void crearBase() {
        raizReinos = new NodoReino("Asgard");
        raizReinos.izquierdo = new NodoReino("Midgard");
        raizReinos.derecho = new NodoReino("Vanaheim");
    }

    void punto1() {
        crearBase();
        System.out.println("Punto 1 -> Raiz: " + raizReinos.nombre
                + " | Izquierda: " + raizReinos.izquierdo.nombre
                + " | Derecha: " + raizReinos.derecho.nombre);
    }

    // Punto 2
    void insertar(int nivelSeguridad) {
        raiz = insertarRec(raiz, nivelSeguridad);
    }

    private Nodo insertarRec(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRec(actual.derecho, valor);
        }
        return actual;
    }

    void punto2() {
        int[] niveles = {50, 30, 70, 20, 40, 60, 80};
        for (int nivel : niveles) {
            insertar(nivel);
        }
        System.out.println("Punto 2 -> Raiz del BST: " + raiz.valor
                + " | Izquierda: " + raiz.izquierdo.valor
                + " | Derecha: " + raiz.derecho.valor);
    }

    // Punto 3
    boolean buscar(int amenaza) {
        return buscarRec(raiz, amenaza);
    }

    private boolean buscarRec(Nodo actual, int amenaza) {
        if (actual == null) {
            return false;
        }
        if (amenaza == actual.valor) {
            return true;
        }
        if (amenaza < actual.valor) {
            return buscarRec(actual.izquierdo, amenaza);
        }
        return buscarRec(actual.derecho, amenaza);
    }

    void punto3() {
        System.out.println("Punto 3 -> buscar(40): " + buscar(40));
        System.out.println("Punto 3 -> buscar(99): " + buscar(99));
    }

    // Punto 4
    void inOrden(Nodo actual) {
        if (actual == null) {
            return;
        }
        inOrden(actual.izquierdo);
        System.out.print(actual.valor + " ");
        inOrden(actual.derecho);
    }

    void punto4() {
        System.out.print("Punto 4 -> InOrden: ");
        inOrden(raiz);
        System.out.println();
    }

    // Punto 5
    void preOrden(Nodo actual) {
        if (actual == null) {
            return;
        }
        System.out.print(actual.valor + " ");
        preOrden(actual.izquierdo);
        preOrden(actual.derecho);
    }

    void punto5() {
        System.out.print("Punto 5 -> PreOrden: ");
        preOrden(raiz);
        System.out.println();
    }

    // Punto 6
    void postOrden(Nodo actual) {
        if (actual == null) {
            return;
        }
        postOrden(actual.izquierdo);
        postOrden(actual.derecho);
        System.out.print(actual.valor + " ");
    }

    void punto6() {
        System.out.print("Punto 6 -> PostOrden: ");
        postOrden(raiz);
        System.out.println();
    }

    // Punto 7
    int contarLineas() {
        return contarLineasRec(raiz);
    }

    private int contarLineasRec(Nodo actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + contarLineasRec(actual.izquierdo) + contarLineasRec(actual.derecho);
    }

    void punto7() {
        System.out.println("Punto 7 -> Total de nodos: " + contarLineas());
    }

    // Punto 8
    Integer encontrarMenor() {
        if (raiz == null) {
            return null;
        }
        Nodo actual = raiz;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.valor;
    }

    Integer encontrarMayor() {
        if (raiz == null) {
            return null;
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.valor;
    }

    void punto8() {
        System.out.println("Punto 8 -> Menor: " + encontrarMenor()
                + " | Mayor: " + encontrarMayor());
    }

    // Punto 9
    int calcularAltura() {
        return calcularAlturaRec(raiz);
    }

    private int calcularAlturaRec(Nodo actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + Math.max(calcularAlturaRec(actual.izquierdo), calcularAlturaRec(actual.derecho));
    }

    void punto9() {
        System.out.println("Punto 9 -> Altura del arbol: " + calcularAltura());
    }

    // Punto 10
    void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarRec(actual.derecho, valor);
        } else {
            // Caso 1: nodo hoja
            if (actual.izquierdo == null && actual.derecho == null) {
                return null;
            }
            // Caso 2: un solo hijo
            if (actual.izquierdo == null) {
                return actual.derecho;
            }
            if (actual.derecho == null) {
                return actual.izquierdo;
            }
            // Caso 3: dos hijos (sucesor inorden)
            Nodo sucesor = actual.derecho;
            while (sucesor.izquierdo != null) {
                sucesor = sucesor.izquierdo;
            }
            actual.valor = sucesor.valor;
            actual.derecho = eliminarRec(actual.derecho, sucesor.valor);
        }
        return actual;
    }

    void punto10() {
        eliminar(20); // hoja
        System.out.print("Punto 10 -> Tras eliminar 20 (hoja): ");
        inOrden(raiz);
        System.out.println();

        eliminar(30); // un hijo
        System.out.print("Punto 10 -> Tras eliminar 30 (un hijo): ");
        inOrden(raiz);
        System.out.println();

        eliminar(50); // dos hijos (raiz)
        System.out.print("Punto 10 -> Tras eliminar 50 (dos hijos): ");
        inOrden(raiz);
        System.out.println();
    }
}
