-- Question 1 --
-- Question 1 (a) --
-- RIGHT --
square_and_cube x = (x ^ 2, x ^ 3)

-- Question 1 (b) --
-- RIGHT --
add_tuple (a, b) = a + b

-- Question 1 (c) --
-- RIGHT --
first x = fst x
second x = snd x

-- Question 1 (d) --
-- RIGHT --
swap (a, b) = (b, a)

-- Question 1 (e) --
-- RIGHT --
two_to_three (a, b) c = (a,b,c)


-- Question 2 --
-- Question 2 (a) --
head_squared list = head list ^ 2

-- Question 2 (b) --
third list = list !! 2

-- Question 2 (c) -- 
second_tail list = tail $ tail list

-- Question 2 (d) --
third_head list = head $ second_tail list

-- Question 2 (e) --
first_plus_last list = head list + last list

-- Question 2 (f) --
prepend_two list a b = a : b : list


-- Question 3 --
-- Question 3 (a) --
two_lengths list1 list2 = length list1 + length list2

-- Question 3 (b) --
make_palindrome list = list ++ reverse list

-- Question 3 (c) --
sum_and_product list = (sum list, product list)

-- Question 3 (d) --
four_through_six list = take 3 $ drop 3 list

-- Question 3 (e) --
both_in list x y = if elem x list && y `elem` list then True else False 


-- Question 5 --
-- Question 5 (b) --
only_odds list = [x | x <- list, x `mod` 2 == 1]

-- Question 5 (c) --
between a b list = [x | x <- list, x > a, x < b]

-- Question 5 (d) --
number_of_es string = length [x | x <- string, x == 'e']

-- Question 5 (e) --
proper_fizzbuzz = [
    let div_3 = x `mod` 3 == 0
        div_5 = x `mod` 5 == 0
    in 
        if div_3 && div_5 then "fizzbuzz"
        else if div_3 then "fizz"
        else if div_5 then "buzz"
        else show x
    | x <- [1..]]