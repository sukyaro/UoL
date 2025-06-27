-- Question 1 --
-- Question 1 (a) --
{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
mult13 1 = 13
mult13 n = 13 + mult13 (n - 1)

-- Question 1 (b) --
pow3 1 = 3
pow3 n = 3 * pow3 (n - 1)

-- Question 1 (c) --
odd_sum 0 = 0
odd_sum n
    | odd n     = n + odd_sum (n - 1)
    | otherwise = odd_sum (n - 1)

-- Question 1 (d) --
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n - 1) + lucas (n - 2)


-- Question 2 --
-- Question 2 (a) --
half_sum [] = 0
half_sum (x:xs) = x / 2 + half_sum xs

-- Question 2 (b) --
mult2 [] = []
mult2 (x:xs) = x * 2 : mult2 xs

-- Question 2 (c) --
drop_evens [] = []
drop_evens (x:xs)
    | odd x     = x : rest
    | otherwise = rest
    where rest = drop_evens xs

-- Question 2 (d) --
triple [] = []
triple (x:xs) = x : x : x : triple xs

-- Question 2 (e) --
mult_adjacent [] = []
mult_adjacent (x:y:xs) = x * y : mult_adjacent xs

-- Question 2 (d) --
get_ele i [] = error "no such an element"
get_ele 1 (x:xs) = x
get_ele i (x:xs) = get_ele (i - 1) xs

-- Question 2 (g) --
drop_ele i [] = error "no element"
drop_ele 1 (x:xs) = xs
drop_ele i (x:xs) = x : drop_ele (i - 1) xs


-- Question 3 --
-- Question 3 (a) --
div_list [] [] = []
div_list (x:xs) (y:ys) = x / y : div_list xs ys

-- Question 3 (b) --
longer [] []    = False
longer [] list2 = False
longer list1 [] = True
longer (x:xs) (y:ys) = longer xs ys

-- Question 3 (c) --
div3_and_not [] = ([],[])
div3_and_not (x:xs)
    | x `mod` 3 == 0 = (x : div3, not)
    | otherwise      = (div3, x : not)
    where (div3, not) = div3_and_not xs

-- Question 3 (d) --
vowels_and_consonants [] = ([],[])
vowels_and_consonants (x:xs)
    | x `elem` "aeiou" = (x : vowels, consonants)
    | otherwise        = (vowels, x : consonants)
    where (vowels, consonants) = vowels_and_consonants xs

-- Question 3 (e) --
fast_lucas_help 1 = [1, 2]
fast_lucas_help n = x + y : (x:y:xs)
    where (x:y:xs) = fast_lucas_help (n - 1)

fast_lucas n = head $ fast_lucas_help n

-- Question 3 (f) --
mult_by_pos_help i [] = []
mult_by_pos_help i (x:xs) = i * x : mult_by_pos_help (i + 1) xs

mult_by_pos = mult_by_pos_help 0