-- Question 1 --
-- Question 1 (a) --
mult13 :: Int -> Int
mult13 0 = 0
mult13 n = 13 + mult13 (n-1)

-- Question 1 (b) --
pow3 :: Int -> Int
pow3 0 = 1
pow3 n = 3 * pow3 (n-1)

-- Question 1 (c) --
odd_sum :: Int -> Int
odd_sum 0 = 0
odd_sum n = if n `mod` 2 /= 0 then n + odd_sum (n-1) else odd_sum (n-1)

-- Question 1 (d) --
lucas :: Int -> Int
lucas 0 = 2
lucas 1 = 1
lucas n = lucas (n-1) + lucas (n-2)


-- Question 2 --
-- Question 2 (a) --
half_sum :: Fractional a => [a] -> a
half_sum [] = 0
half_sum (x:xs) = x / 2 + half_sum xs 

-- Question 2 (b) --
mult2 :: Num a => [a] -> [a]
mult2 [] = []
mult2 (x:xs) = x * 2 : mult2 xs

-- Question 2 (c) --
drop_evens :: Integral a => [a] -> [a]
drop_evens [] = []
drop_evens (x:xs)
    | x `mod` 2 /= 0 = x : drop_evens xs
    | otherwise      = drop_evens xs

-- Question 2 (d) --
triple :: [a] -> [a]
triple [] = []
triple (x:xs) = [x, x, x] ++ triple xs

-- Question 2 (e) --
mult_adjacent :: Integral a => [a] -> [a]
mult_adjacent [] = []
mult_adjacent (x:y:xs) = (x * y) : mult_adjacent xs

-- Question 2 (f) --
get_ele :: (Eq a, Num a) => a -> [b] -> b
get_ele i [] = error "no elem"
get_ele 0 (x:xs) = x
get_ele i (x:xs) = get_ele (i - 1) xs

-- Question 2 (g) --
drop_ele :: (Eq a, Num a) => a -> [b] -> [b]
drop_ele i [] = error "no such an element"
drop_ele 0 (x:xs) = xs
drop_ele i (x:xs) = x : drop_ele (i - 1) xs


-- Question 3 --
-- Question 3 (a) --
div_list [] [] = []
div_list (x:xs) (y:ys) = (x / y) : div_list xs ys

-- Question 3 (b) --
longer :: [a] -> [b] -> Bool
longer [] [] = False
longer list1 [] = True
longer [] list2 = False
longer (x:xs) (y:ys) = longer xs ys 

-- Question 3 (c) --
div3_and_not [] = ([],[])
div3_and_not (x:xs)
    | x `mod` 3 == 0 = (x : div3, not_div3)
    | otherwise      = (div3, x : not_div3)
    where (div3, not_div3) = div3_and_not xs

-- Question 3 (d) --
vowels_and_consonants [] = ([],[])
vowels_and_consonants (x:xs)
    | elem x "aeiou" = (x : vowels, consonants)
    | otherwise      = (vowels, x : consonants)
    where (vowels, consonants) = vowels_and_consonants xs

-- Question 3 (e) --
fast_lucas_help 1 = [1,2]
fast_lucas_help n = x + y : (x:y:xs)
    where (x:y:xs) = fast_lucas_help (n - 1)

fast_lucas n = head (fast_lucas_help n)

-- Question 3 (f) --
mult_by_pos_help pos [] = []
mult_by_pos_help pos (x:xs) = x * pos : mult_by_pos_help (pos + 1) xs

mult_by_pos list = mult_by_pos_help 0 list


-- Question 4 --
collatz_sequence 1 = [1]
collatz_sequence n
    | n `mod` 2 == 0 = n : collatz_sequence (div n 2)
    | otherwise      = n : collatz_sequence (3 * n + 1)

collaz_lenghts 1 = [(1, 1)]
collaz_lenghts n = [(n, length $ collatz_sequence n)] ++ collaz_lenghts (n - 1)

longest_collats_1 list = foldr (\(x, y) acc -> if y > snd acc then (x, y) else acc) (0, 0) list

longest_collats n = fst (longest_collats_1 $ collaz_lenghts n)