**Disadvantage of this approach**

The main disadvantages are:
1)Code duplication
    Every controller needs the same validation code.
2)Violates the DRY (Don't Repeat Yourself) principle
    The same logic is written multiple times.
3)Hard to maintain
    If the validation mechanism changes, every controller must be modified.
4)Creates a ValidatorFactory on every request
    This is unnecessary and inefficient.
5)Controllers become cluttered
    Controllers should mainly handle HTTP requests, not validation logic.