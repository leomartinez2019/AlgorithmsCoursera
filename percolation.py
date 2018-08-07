# Code for percolation
# Example of array 4x4
# [0,0,0,0,0,5,6,7,8,9,10,11,12,17,17,17,17,17]
# first element is the top virtual site (0)
# last element is the bottom virtual site (17)

class Percolation(object):
    def __init__(self, N):
        self.array = [0]*(N+1) + list(range(N+1,N*N-N+1,1)) + [N*N+1]*(N+1)
        self.open_sites = [False]*N**2

    def __str__(self):
        return str(self.array)

    def open(self, row, col):
    'open site, check for union'
        pass

    def is_open(self, row, col):
        'return True or Flase'
        pass

    def is_full(self, row, col):
        'open site connected to open site in the top row'
        pass

    def number_of_open_sites(self):
        'get number of open sites'
        pass

    def percolates(self):
        'site in the bottom is full'
        pass

    def rowcol_to_index(row, col, N):
        'transform row / col to indx, assume array starts with elem 1, not 0'
        indx = N * (row - 1) + col
        return indx


p = Percolation(5)
print(p)
