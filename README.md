# PP Lab 5 – Greedy Knapsack Problem Solver

Projekt demonstracyjny w Javie prezentujący **zachłanne rozwiązanie problemu plecakowego**  
(*unbounded / 0-∞ knapsack* – można użyć dowolnej liczby egzemplarzy każdego przedmiotu).

---

## Spis treści
1. [Opis](#opis)  
2. [Wymagania](#wymagania)  
3. [Budowanie](#budowanie)  
4. [Uruchamianie](#uruchamianie)  
5. [Testy jednostkowe](#testy-jednostkowe)  
6. [Struktura katalogów](#struktura-katalogów)  
7. [Jak to działa?](#jak-to-działa)  
8. [Dalszy rozwój](#dalszy-rozwój)  
9. [Licencja](#licencja)

---

## Opis

Program generuje losowo **`n` typów przedmiotów** (*wartość*, *waga*) w ustalonym przedziale,  
a następnie, dla zadanej **pojemności plecaka `C`**, wybiera przedmioty tak, by:

* zapełnić plecak w największym możliwym stopniu (≤ `C`),  
* zmaksymalizować łączną wartość.

Algorytm sortuje indeksy przedmiotów malejąco według stosunku *value / weight*, po czym
 – zachłannie – wrzuca do plecaka maksymalną możliwą liczbę egzemplarzy kolejnych typów.

Plik **`Main.java`** pokazuje minimalny przykład użycia klasy `Problem`.

---

## Wymagania

| Narzędzie | Wersja minimalna |
|-----------|------------------|
| **JDK**   | 19               |
| **Maven** | 3.8              |

---

## Budowanie

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="org.plecak.Main"
```
