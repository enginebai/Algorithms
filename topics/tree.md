# Tree
## Definitions & Terminology
A *tree* is a finite set of *nodes* such that:
* Specially designated node called the *root*.
* The remaining nodes are the disjoint sets `T1, ..., Tn` where `Ti` is a tree, they are called the *subtrees* of the root. `T1, ..., Tn` are disjoint sets prohibits subtrees from connecting together (no cross breeding).

![Tree](../media/tree.png)

* A *node* stands for the item of data and branches to other nodes.
* The number of subtrees of a node is called its *degree*. For example, the degree of `A` is 3, `C` is 1, `E` is 0. The degree of a tree is the maximum degree, for this tree is degree 3.
* For those nodes that have degree zero are called *leaf*. (`{E, F, G, K, I, J}`)
* The root of the subtree is the *parent*, and the subtree is the *children*. (`B` is parent of `{E, F}`, whereas `{E, F}` are children of `B`)
* Children of the same parent are called *siblings*. (`{B, C, D}`, `{E, F}`, `{H, I, J}`)
* The *ancestor* of a node are all the nodes along the path from the root to that node, the ancestor for node `K` are `{A, D, H}`.
* The *height* or *depth* of a tree is the maximum level, for the tree is 4.
* A *forest* is the disjoint sets `T1, ..., Tn` that remove the root of the tree. (`{B, E, F}`, `{C, G}`, `{D, H, I, J, K}`)


## Sub-toptics
* Heap
* Priority
* Binary Tree
* Binary Search Tree
* BFS/DFS

## Resources
- [ ] Fundamental of Data Structure
- [ ] CLRS (Simple)
- [ ] CTCI
- [ ] [MIT 6.006 Introduction to Algorithm - Lecture 6: Binary Trees, Part 1](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/lecture-6-binary-trees-part-1/)
- [ ] http://alrightchiu.github.io/SecondRound/treeshu-introjian-jie.html // Nice introductory note
- [ ] https://leetcode-solution-leetcode-pp.gitbook.io/leetcode-solution/thinkings/tree // Nice introductory note
- [ ] https://github.com/youngyangyang04/leetcode-master#%E4%BA%8C%E5%8F%89%E6%A0%91 // Nice introductory note
- [ ] [Google Tech Dev Guide](https://techdevguide.withgoogle.com/paths/data-structures-and-algorithms/#sequence-3)
- [ ] [LC Learn](https://leetcode.com/explore/learn/card/data-structure-tree/) 
- [ ] [LC Top Interview Questions](https://leetcode.com/explore/interview/card/top-interview-questions-easy/94/trees/) // Coding problems With easy/medium/hard levels
- [ ] [Google Recuriter Recommended Problems List](https://turingplanet.org/2020/09/18/leetcode_planning_list/#Tree) 
- [ ] [Coding Interview University](https://github.com/jwasham/coding-interview-university#trees) // Simple note
- [ ] [Tech Interview Handbook](https://www.techinterviewhandbook.org/algorithms/tree) // Simple note + relative coding problems
- [ ] [Software Engineering Interview Preparation](https://github.com/orrsella/soft-eng-interview-prep/blob/master/topics/data-structures.md#binary-search-trees) // Binary search tree, cheat sheet
- [ ] https://github.com/TSiege/Tech-Interview-Cheat-Sheet#binary-tree // Simple note
- [ ] [Stadford Foundations of Computer Science - The Tree Data Model](http://infolab.stanford.edu/~ullman/focs/ch05.pdf)
