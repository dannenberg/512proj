import sys

if(len(sys.argv) < 2):
    exit()
idname = sys.argv[1]

if "1" not in idname:
    exit()

with open(idname, "r") as f:
    orig = f.read()

for x in xrange(2, 65):
    with open(idname.replace("1", str(x)), "w") as f:
        f.write(orig.replace("1", str(x)))
