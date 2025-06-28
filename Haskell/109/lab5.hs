-- Question 2 --
-- Question 2 (a) --
plus_ten = (+) 10

-- Question 2 (b) --
is_twenty = (==) 20

-- Question 2 (c) --
three_power = (^) 3

-- Question 2 (d) --
power_three = (^3) 

-- Question 2 (e) --
take_four = (take 4)


-- Question 3 --
-- Question 3 (a) --
quest_3_a inp = (\x -> x - 1) inp

-- Question 3 (b) --
quest_3_b x y = (\ x y -> x + y) x y

-- Question 3 (c) --
quest_3_c x y = (\ x y -> show x ++ show y) x y

-- Question 3 (d) --
quest_3_d tuple = (\ (x, y) -> (y, x)) tuple

-- Question 3 (e) --
quest_3_e list = (\ x -> x !! 1) list


-- Question 4 --
-- Question 4 (a) --
triple :: Num a => [a] -> [a]
triple list = map (*3) list

-- Question 4 (b) --
list_to_string :: Show a => [a] -> [String]
list_to_string list = map (show) list

-- Question 4 (c) --
second_char :: [[Char]] -> [Char]
second_char list = map (!! 1) list

-- Question 4 (d) --
add_pairs :: Num a => [(a, a)] -> [a]
add_pairs list = map (\ (x,y) -> x + y) list

-- Question 4 (e) --
triple_list_list :: Num a => [[a]] -> [[a]]
triple_list_list list = map (map (*3)) list


-- Question 5 --
-- Question 5 (a) --
only_odds :: Integral a => [a] -> [a]
only_odds list = filter (odd) list

-- Question 5 (b) --
vowels :: [Char] -> [Char]
vowels string = filter (\x -> x `elem` "aeiou") string

-- Question 5 (c) --
between :: Ord a => a -> a -> [a] -> [a]
between a b list = filter (\x -> x > a && x < b) list

-- Question 5 (d) --
ordered :: Ord a => [(a,a)] -> [(a,a)]
ordered list = filter (\ (a, b) -> a > b) list

-- Question 5 (e) --
singletons :: [[Char]] -> [[Char]]
singletons list = filter (\x -> length x == 1) list

-- Question 5 (f) --
only_odds_list :: Integral a => [[a]] -> [[a]]
only_odds_list list = map (filter (odd)) list


-- Question 6 --
-- Question 6 (a) --
quest_6_a list = (head . head) list

-- Question 6 (b) --
quest_6_b inp = ((+1) . (*2)) inp

-- Question 6 (c) --
quest_6_c list = (sum . tail . tail) list

-- Question 6 (d) --
quest_6_d list = (filter (>10) . map (*2)) list