# Quick find algorithm from Coursera course

class WeightedQuickUnion(object):
    def __init__(self, size):
        self.d = list(range(size))
        self.counter = [1]*size

    def root(self, num):
        'find root of an index'
        indx = num
        while indx != self.d[indx]:
            indx = self.d[indx]
        return indx

    def connected(self, p, q):
        'find if two elements are connected'
        return self.root(p) == self.root(q)

    # TODO: make sure to merge small trees to bigger trees correctly
    # Have to change all roots to the upper root
    def union(self, p, q):
        'merge two elements by equaling their values'
        p_root = self.root(p)
        q_root = self.root(q)
        if self.counter[p_root] <= self.counter[q_root]:
            self.d[p_root] = q_root
            self.counter[q_root] += self.counter[p_root]
            self.counter[p_root] = 0
        else:
            self.d[q_root] = p_root
            self.counter[p_root] += self.counter[q_root]
            self.counter[q_root] = 0

def test1():
    instancia = WeightedQuickUnion(10)
    instancia.union(4,3)
    instancia.union(3,8)
    instancia.union(6,5)
    instancia.union(9,4)
    instancia.union(2,1)
    assert instancia.connected(8,9) == True, "Test failed"
    assert instancia.connected(5,4) == False, "Test failed"
    assert instancia.connected(4,3) == True, "Test failed"
    #assert instancia.root(3) == 3, "Test failed"
    instancia.union(5,0)
    instancia.union(7,2)
    instancia.union(6,1)
    #instancia.union(7,3)
    print("Tests passed")
    print(instancia.d)
    print(instancia.counter)

test1()
