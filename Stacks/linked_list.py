class Node(object):
    def __init__(self):
        self.item = None
        self.next = None

    def __str__(self):
        return self.item

first = Node()
first.item = "to"

second = Node()
second.item = "be"
first.next = second

third = Node()
third.item = "or"
second.next = third

# insert at the end
oldlast = third
third = Node()
third.item = "not"
oldlast.next = third

# Traverse the nodes
def traversing():
    x = first
    while x != None:
        print(x.item)
        x = x.next

traversing()
