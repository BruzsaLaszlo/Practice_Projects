import java.util.*;

enum Color {RED, GREEN}

abstract class Tree {

    private final int value;
    private final Color color;
    private final int depth;

    protected Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private final ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sum;

    public int getResult() {
        //implement this
        return sum;
    }

    public void visitNode(TreeNode node) {
        //implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long value = 1;

    public int getResult() {
        //implement this
        return (int) value % 1_000_000_007;
    }

    public void visitNode(TreeNode node) {
        //implement this
        if (node.getColor() == Color.RED) {
            value *= node.getValue();
            value %= 1_000_000_007;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        if (leaf.getColor() == Color.RED) {
            value *= leaf.getValue();
            value %= 1_000_000_007;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int sumN;
    private int sumL;

    public int getResult() {
        //implement this
        return Math.abs(sumN - sumL);
    }

    public void visitNode(TreeNode node) {
        //implement this
        if (node.getDepth() % 2 == 0) {
            sumN += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        if (leaf.getColor() == Color.GREEN) {
            sumL += leaf.getValue();
        }
    }
}

public class JavaVisitorPattern {

    private static Map<Integer, List<Integer>> edges = new HashMap<>();
    private static String[] values;
    private static String[] colors;

    private static Color getColor(int i) {
        return colors[i - 1].equals("0") ? Color.RED : Color.GREEN;
    }

    private static int getValue(int i) {
        return Integer.parseInt(values[i - 1]);
    }

    public static Tree solve(String input) {
        //read the tree from STDIN and return its root as a return value of this function
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        sc.nextLine();
        values = sc.nextLine().split(" ");
        colors = sc.nextLine().split(" ");


        for (int i = 0; i < n - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges.putIfAbsent(a, new ArrayList<>());
            edges.computeIfPresent(a, (key, list) -> {
                list.add(b);
                return list;
            });
            edges.putIfAbsent(b, new ArrayList<>());
            edges.computeIfPresent(b, (key, list) -> {
                list.add(a);
                return list;
            });
        }

        TreeNode root = new TreeNode(getValue(1), getColor(1), 0);

        createTree(root, 1, 1);

        return root;
    }

    private static void createTree(TreeNode root, int r, int i) {
        TreeNode node;
        if (r != i) {
            if (edges.get(i).size() == 1) {
                root.addChild(new TreeLeaf(getValue(i), getColor(i), root.getDepth() + 1));
                return;
            }
            node = new TreeNode(getValue(i), getColor(i), root.getDepth() + 1);
            root.addChild(node);
            edges.computeIfPresent(i, (key, list) -> {
                list.remove((Integer) r);
                return list;
            });
        } else {
            node = root;
        }
        for (int j : edges.get(i)) {
            createTree(node, i, j);
        }
    }

    public static final String INPUT = """
            100
            863 168 757 485 356 683 94 592 675 579 697 864 690 205 616 393 451 846 526 394 10 446 910 323 18 661 866 588 110 783 859 897 96 819 87 167 351 942 924 234 717 533 252 495 612 105 958 62 174 467 711 557 757 965 605 855 49 139 579 71 971 176 672 368 720 862 588 294 621 752 458 200 862 973 856 607 352 222 761 937 32 109 172 304 819 373 53 911 531 848 187 123 359 591 356 252 669 584 581 531
            1 1 0 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 1 0 1 1 0 0 0 1 1 1 0 1 0 1 0 1 0 1 1 0 0 1 0 1 1 1 0 1 1 0 1 1 0 1 1 0 0 1 0 0 1 0 1 1 1 0 1 0 0 1 0 0 0 1 1 0 0 0 0 0 1 1 0 0 0 1 0
            1 33
            1 66
            1 35
            1 6
            1 41
            1 10
            1 96
            1 46
            1 81
            1 83
            1 20
            1 89
            1 58
            1 92
            1 61
            1 30
            1 32
            2 33
            2 49
            2 4
            2 40
            2 11
            2 44
            2 77
            2 17
            2 28
            2 19
            2 36
            2 21
            2 54
            2 88
            2 26
            2 59
            2 76
            2 75
            2 86
            3 99
            3 70
            3 94
            3 41
            3 43
            3 78
            3 47
            3 16
            3 15
            3 62
            4 68
            4 34
            4 9
            4 45
            4 79
            4 51
            4 52
            4 53
            4 25
            4 27
            4 93
            5 97
            5 33
            5 67
            5 72
            5 8
            5 85
            5 31
            6 55
            6 13
            6 14
            6 48
            6 22
            6 23
            6 57
            7 97
            7 38
            7 73
            7 12
            7 50
            7 24
            7 29
            8 100
            8 80
            8 18
            8 87
            8 56
            8 95
            9 65
            9 71
            9 74
            9 82
            9 64
            10 39
            11 84
            12 69
            12 42
            12 91
            12 37
            13 90
            13 98
            14 60
            17 63""";

    public static void main(String[] args) {
        Tree root = solve(INPUT);
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}