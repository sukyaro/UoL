-- Question 8 --
-- Question 8 (a) --
-- RIGHT --
minus_one x = x - 1

-- Question 8 (b) --
-- RIGHT --
quad_power x = 4 ^ x

-- Question 8 (c) --
-- RIGHT --
add_three x y z = x + y + z

-- Question 8 (d) --
-- RIGHT --
area r = pi * r ^ 2


-- Question 9 --
-- Question 9 (a) --
-- RIGHT --
mod_three x = x `mod` 3

-- Question 9 (b) --
-- RIGHT --
mod3or5 x
    | x `mod` 3 == 0 = True
    | x `mod` 5 == 0 = True
    | otherwise      = False

-- Question 9 (c) --
-- RIGHT --
min_max a b c d = (a `min` b) + (c `max` d)

-- Question 9 (d) --
-- RIGHT --
quadratic a b c = ((-b) + sqrt(b ** 2 - 4 * a * c)) / (2 * a)


-- Question 13 --
-- Question 13 (a) --
-- RIGHT --
qt_100 x
    | x > 100   = 1
    | otherwise = 0

-- Question 13 (b) --
-- RIGHT --
switch x y c 
    | c == 1    = x
    | otherwise = y

-- Question 13 (c) --
-- RIGHT --
my_max x y
    | x > y     = x
    | otherwise = y

-- Question 13 (d) --
-- RIGHT --
fizzbuzz x = if x `mod` 3 == 0 && x `mod` 5 == 0 then "Fizzbuzz!" else "Nope"


-- Question 14 --
-- Question 14 (a) --
-- RIGHT --
question1 x = let a = x * x in 2 * a

-- Question 14 (b) --
-- RIGHT --
question2 x = 
    let 
        a = x + 1
        b = a * a
        c = 2 ^ b
    in 
        a + b + c  

-- Question 14 (c) --
-- RIGHT --
bounded_square x
    | x * x < 100 = x * x
    | otherwise   = 100

