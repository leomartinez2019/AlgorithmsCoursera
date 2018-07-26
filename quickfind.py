# Quick find algorithm from Coursera course

class QuickFind(object):
    def __init__(self, size):
        self.d = list(range(size))
        self.size = size

    def connected(self, p, q):
        'find if two elements are connected'
        # This is not good practice (I left it so I won't forget):
        #if self.d[p] == self.d[q]:
        #    return True
        #return False
        # Instead:
        return self.d[p] == self.d[q]

    def union(self, p, q):
        'merge two elements by equaling their values'
        if not self.connected(p, q):
            p_indx = self.d[p]
            q_indx = self.d[q]
            for indx in range(self.size):
                if self.d[indx] == p_indx:
                    self.d[indx] = q_indx

# Initial testing
def test1():
    'testing both operations / not unit testing here :('
    k = QuickFind(10)
    k.union(4,3)
    print(k.d)
    k.union(3,8)
    print(k.d)
    k.union(6,5)
    print(k.d)
    k.union(9,4)
    print(k.d)
    k.union(2,1)
    print(k.d)
    assert k.connected(8,9) == True, "Test failed"
    assert k.connected(7,8) == False, "Test failed"
    print("Test passed")

