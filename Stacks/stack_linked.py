class Node(object):
    def __init__(self):
        self.item = None
        self.next = None

    def __str__(self):
        return self.item

class LinkedStackOfStrings(object):
    def __init__(self):
        self.first = Node()

    def is_empty(self):
        return self.first.item == None

    def push(self, item):
        oldfirst = self.first
        self.first = Node()
        self.first.item = item
        self.first.next = oldfirst

    def pop(self):
        item = self.first.item
        self.first = self.first.next
        return item

s = LinkedStackOfStrings()
s.push('coco')
s.push('papa')
print(s.pop())
