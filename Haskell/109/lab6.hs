-- Question 1 --
-- Quesiton 1 (a) --
{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
import Data.ByteString.Char8 (split)
{-# HLINT ignore "Use camelCase" #-}
list_product list = foldr (*) 1 list

-- Question 1 (b) --
list_any list = foldr (||) False list

-- Question 1 (c) --
product_of_evens list = foldr (\x acc -> if even x then x * acc else acc) 1 list

-- Question 1 (d) --
lt10 :: (Foldable t, Ord a, Num a, Num b) => t a -> b
lt10 list = foldr (\x acc -> if x < 10 then acc + 1 else acc) 0 list

-- Question 1 (e) -- 
smalls string = foldr (\x acc -> if x `elem` ['a'..'z'] then x : acc else acc) "" string

-- Question 1 (f) --
sum_evens_odds list = foldr (\x (a, b) -> if even x then (a + 1, b) else (a, b + 1)) (0,0) list

-- Question 1 (g) --
list_product_1 = scanr (*) 1

list_any_1 = scanr (||) False

product_of_evens_1 = scanr (\x acc -> if even x then x * acc else acc) 1

lt10_1 = scanr (\x acc -> if x < 10 then x + acc else acc) 0

smalls_1 = scanr (\x acc -> if x `elem` ['a'..'z'] then x : acc else acc) ""

sum_evens_odds_1 = scanr (\x (a, b) -> if even x then (a + 1, b) else (a, b + 1)) (0, 0)


-- Question 2 --
-- Question 2 (a) --
leading_caps = takeWhile (`elem` ['A'..'Z'])

-- Question 2 (b) --
drop_caps = dropWhile (`elem` ['a'..'z'])

-- Question 2 (c) --
split_on c string = let
    before = takeWhile (/= c) string
    after  = dropWhile (== c) (dropWhile (/= c) string)
    in
        (before, after)

-- Question 2 (d) --
from_csv "" = []
from_csv string = let
    (first, second) = split_on ',' string
    in
        first : from_csv second



-- Question 3 --
-- Question 3 (a) --
mul_lists list1 list2 = zipWith (*) list1 list2

-- Question 3 (b) --
add_lists list1 list2 = zipWith (&&) list1 list2

-- Question 3 (c) --
keep_or_zero l1 l2 = zipWith (\x y -> if y then x else 0) l1 l2

-- Question 3 (d) --
is_palindrome string
    | string == reverse string = True
    | otherwise                = False