package Problema2;

import java.util.ArrayList;
import java.util.Random;

public class OrdenArrays {

	public static void main(String[] args) {
		int[] array = generaListaEnteros();
		System.out.println("Array generado: ");
		for (int numero : array) {
			System.out.println(numero);
		}
		
		System.out.println("ArrayList con pares-impares: ");
		ArrayList<Integer> lista = ordenaParesImpares(array);
		for (int numero : lista) {
			System.out.println(numero);
		}
	}
	
	public static ArrayList<Integer> ordenaParesImpares(int [] array) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		ArrayList<Integer> pares = new ArrayList<Integer>();
		ArrayList<Integer> impares = new ArrayList<Integer>();
		for (int entero : array) {
			if ((entero % 2) == 0) {
				pares.add(entero);
			} else {
				impares.add(entero);
			}
		}
		lista.addAll(pares);
		lista.addAll(impares);
		return lista;
	}

	public static int [] generaListaEnteros() {
		int [] numeros = new int [10];
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			numeros[i] = rnd.nextInt(51);
		}
		return numeros;
	}
}
