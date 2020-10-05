# -*- coding: utf-8 -*-

import math
import sys
import time

NUM_REQUIRED_SETS = 10

def main():
    sys.stderr.write("Working")
    task1()
    print()
    task2()



def task1():
    """
    We are looking for sets of positive integers that satasfy the following conditions:
        • x^2 + y^2 = 1 + z³
        • z < x < y
        • x, y and z are co-prime.
    """
    num_found_sets = 0
    x = 0
    while (num_found_sets < NUM_REQUIRED_SETS):
        z = 1
        while z < x and num_found_sets < NUM_REQUIRED_SETS:
            ySquared = z**3-x**2+1
            y = perfectSquare(ySquared)
            if (y > 0):
                if (x < y and areCoprime(z, x, y)):
                    num_found_sets += 1
                    print(num_found_sets, x, y, z)
            z += 1
        x += 1

def task2():
    """
    We are looking for sets of positive integers that satasfy the following conditions:
        • x^2 + y^2 = 1 + z³
        • z < x < y
        • x, y and z are co-prime.

    We know x < y 
    So,     x² < y²

    and that x² + y² = 1 + z³
    So,      y² = z³ + 1 - x²

    By substitution:  x² < z³ + 1 - x² 
                    = x² < (z³ + 1)/2 <-
                    = x  < √((z³ + 1)÷2)    
    """
    num_found_sets = 0
    z = 1
    while (num_found_sets < NUM_REQUIRED_SETS):
        x = 1
        while (x < math.sqrt((z**3 + 1)/2) and num_found_sets < NUM_REQUIRED_SETS):
            # Need to make sure y² is a perfect square before taking the root
            ySquared = z**3 - x**2 + 1
            y = perfectSquare(ySquared)
            if (y > 0):
                if (z < x < y and areCoprime(z, x, y)):
                    num_found_sets += 1
                    print(num_found_sets, x, y, z)
            x += 1
        z += 1

def perfectSquare(num):
    """
        If a number is a perfect square, return it's square root.
        Otherwise returns an int < 0
    """
    # Negative number cannot be perfect square
    if (num < 0):
        return False

    root = int(math.sqrt(num))
    if root ** 2 == num:
        return root
    return False

def areCoprime(a, b, c):
    """
        Returns true if a, b, c are all coprime 
    """
    if (math.gcd(a, b) > 1): return False
    if (math.gcd(b, c) > 1): return False
    if (math.gcd(c, a) > 1): return False
    return True


if __name__ == "__main__":
    main()