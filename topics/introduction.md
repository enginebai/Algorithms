# Introduction
## Goals of Learning Data Structure
1. Be able to describe DSA in high-level and abstract way.
2. Design and implement the DSA.
3. Anallysis of DSA: average and worst case.

**Definition**: An *algorithms* is a finite set of instructions that accomplish a particular task. And it meets the following criteria:
1. Input
2. Output
3. Definiteness: Each steps must be concise.
4. Finiteness: It will terminate within a finite steps.
5. Effectiveness: Be carried out by only pen and paper + feasible.

## Abstract Data Structure (ADT)
1. 設計資料結構的第一步：知道規格而不需要知道如何實作，也就是以「抽象」的（數學模型）方式去理解和設計，可以當作純粹理論的實體，用來簡化描述抽象演算法、分類和評價資料結構，抽象資料結構的選擇決定了演算法的設計以及評估複雜度。
2. 定義的時候，我們需要知道 `Domain` + `Functions` + `Axiom`，這時候我們只定義「行為」+「介面」，不定義實作細節（介面實作分離，使用者只關心公開的介面，不知道且不在意如何實作、也不受實作影響）。

<img src='../media/ADT.png' alt='ADT'/>

3. 資料結構就是一個 ADT 不斷做 refinement 的過程，一直到所有運算都能夠「直接執行的函式」表示出來為止。

以抽象的 List 的規格為例：

```md
# Domain
1. A collection with the same type.
2. The items in the list are ordered.

# Function
1. Get(i): Return the i-th item.
2. Add(i, element): Add element to the i-th index.
3. Replace(i, element): Replace the i-th item with element.
4. Remove(i): Remove item from the list.
5. Size(i): Get the list size.
```

或者 Set：

```md
# Domain
1. A collection with the same type.
2. The items in the set are unordered and unique.

# Function
1. Add(i, element)
2. Remove(i)
3. Search(element)
```

> Source: Fundamental of Data Structure

## Goals of Algorithms
Be able to communicate the idea to people how you solve the problem and convince them that they are correct and better than other things.

1. Solve computational problems.
2. Prove correctness
3. Argue efficiency

### Correctness
One of the nice things about induction is that is isolates our problem to not consider everthing all at once, but break it down into smaller interface so that we can do less work at each step.

> Source: MIT
> Source: CLRS

## Resources
- [ ] Fundamental of Data Structure
- [ ] CLRS
- [ ] [MIT](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-spring-2020/lecture-videos/index.htm)
- [ ] [Tech Interview Handbook](https://techinterviewhandbook.org/algorithms/introduction/)