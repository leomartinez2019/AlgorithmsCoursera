import unittest
from quickfind import QuickFind

class TestQuickFindClass(unittest.TestCase):

    def setUp(self):
        self.quick_find = QuickFind(10)
        self.quick_find.union(8,9)

    def test_union(self):
        lista = self.quick_find.d
        self.assertEqual(lista[8], lista[9])

    def test_connect(self):
        self.assertTrue(self.quick_find.connected(8,9))

if __name__ == '__main__':
    unittest.main()
