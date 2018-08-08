# Code for percolation
# Example of array 4x4
# [0,0,0,0,0,5,6,7,8,9,10,11,12,17,17,17,17,17]
# first element is the top virtual site (0)
# last element is the bottom virtual site (17)

class Percolation(object):
    def __init__(self, N):
        self.array = [0]*(N+1) + list(range(N+1,N*N-N+1,1)) + [N*N+1]*(N+1)
        self.open_sites = [False]*(N**2 + 1)
        self.dim = N
        self.counter = [1] * (N*N + 2)
        self.open_sites_counter = 0

    def __str__(self):
        return str(self.array)

    def join_adjacents(self, row, col):
        'helper function for function open, check if there are open adjacent sites'
        site = self.rowcol_to_index(row, col)
        up = row + 1
        down = row - 1
        left = col - 1
        right = col + 1
        if self.is_open(up, col):
            up_site = self.rowcol_to_index(up, col)
            self.union(up_site, site)
        if self.is_open(row, left):
            left_site = self.rowcol_to_index(row, left)
            self.union(left_site, site)
        if self.is_open(row, right):
            right_site = self.rowcol_to_index(row, right)
            self.union(right_site, site)
        if self.is_open(down, col):
            down_site = self.rowcol_to_index(down, col)
            self.union(down_site, site)

    def open(self, row, col):
        'open site, check for union'
        indx = self.rowcol_to_index(row, col)
        if not self.open_sites[indx]:
            self.open_sites[indx] = True
            self.open_sites_counter += 1
            self.join_adjacents(row, col)

    def is_open(self, row, col):
        'return True or False'
        indx = self.rowcol_to_index(row, col)
        return self.open_sites[indx]

    def is_full(self, row, col):
        'open site connected to open site in the top row'
        indx = self.rowcol_to_index(row, col)
        virtual_top = self.array[0]
        return self.connected(indx, virtual_top)

    def number_of_open_sites(self):
        'get number of open sites'
        return self.open_sites_counter

    def percolates(self):
        'site in the bottom is full'
        virtual_bottom = self.array[-1]
        virtual_top = self.array[0]
        return self.connected(virtual_bottom, virtual_top)

    def rowcol_to_index(self, row, col):
        'transform row / col to indx, assume array starts with elem 1, not 0'
        if row not in range(1,self.dim + 1) or col not in range(1,self.dim + 1):
            return 0
        indx = self.dim * (row - 1) + col
        return indx

    def root(self, num):
        'find root of an index'
        indx = num
        while indx != self.array[indx]:
            self.array[indx] = self.array[self.array[indx]]
            indx = self.array[indx]
        return indx

    def connected(self, p, q):
        'find if two elements are connected'
        return self.root(p) == self.root(q)

    def union(self, p, q):
        'merge two elements by equaling their values'
        i = self.root(p)
        j = self.root(q)
        if i == j:
            return
        if self.counter[i] < self.counter[j]:
            self.array[i] = j
            self.counter[j] += self.counter[i]
        else:
            self.array[j] = i
            self.counter[i] += self.counter[j]


# Percolation client and test
import random

def percolation_client(N):
    #random.seed(100)
    loops = 10000
    #N = 100
    perc = Percolation(N)
    contador = 0
    while not perc.percolates():
        row = random.randint(1,N)
        col = random.randint(1,N)
        perc.open(row, col)
        #contador += 1
        #if contador == loops:
            #break
    return perc.open_sites_counter

def average_perc():
    N = 20
    tries = 10
    t = 0
    for elem in range(tries):
        t += percolation_client(N)
    print('Promedio de {} intentos: {}'.format(tries, t / (tries*N*N)))

average_perc()
