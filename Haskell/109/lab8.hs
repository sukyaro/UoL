data Direction = North | East | South | West deriving (Show, Eq, Read, Ord)

-- Question 2 --
-- Question 2 (d) --
is_north :: Direction -> Bool
is_north input = if input == North then True else False

-- Question 2 (e) --
dir_to_int :: Direction -> Int
dir_to_int input
    | input == North = 1
    | input == East  = 2
    | input == South = 3
    | input == West  = 4


data Point = Point Int Int deriving (Show, Read)

-- Question 3 --
-- Question 3 (a) --
same :: Int -> Point
same x = Point x x

-- Question 3 (b) --
is_zero :: Point -> Bool
is_zero (Point 0 0) = True
is_zero _           = False

-- Question 3 (c) --
mult_point :: Point -> Int
mult_point (Point x y) = x * y

-- Question 3 (d) --
up_two :: Point -> Point
up_two (Point x y) = Point x (y + 2)

-- Question 3 (e) --
add_points :: Point -> Point -> Point
add_points (Point a b) (Point c d) = Point (a + c) (b + d)


-- Question 4 --
-- Question 4 (b) --
not_nothing :: Eq a => Maybe a -> Bool
not_nothing input
    | input /= Nothing = True
    | otherwise        = False

-- Question 4 (c) --
safe_tail :: [a] -> Maybe [a]
safe_tail list
    | length list == 0 = Nothing
    | otherwise        = Just (tail list)

-- Question 4 (d) --
mult_maybe :: Maybe Int -> Maybe Int -> Maybe Int
mult_maybe (Just x) (Just y) = Just (x * y)
mult_maybe _         _       = Nothing


-- Quesiton 5(b) --
return_two :: Int -> Either Bool Char
return_two n
    | n == 1 = Left True
    | otherwise = Right 'a'

-- Question 5(c) --
show_right :: Either String Int -> String
show_right (Left x) = x
show_right (Right y) = show y


data List a = Cons a (List a) | Empty deriving (Show, Read)
-- Question 6 (b) --
-- Cons 1 (Cons 2 (Cons 3 (Cons 4 Empty))) --
-- 1 `Cons` (2 `Cons` (3 `Cons` (4 `Cons` Empty))) --

-- Question 6 (c) --
is_empty :: List a -> Bool
is_empty Empty = True
is_empty _     = False

-- Question 6 (d) --
is_single :: List a -> Bool
is_single (Cons x Empty) = True
is_single _              = False

-- Question 6 (e) --
mult :: List Int -> Int
mult (Empty) = 1
mult (Cons x y) = x * mult y

-- Question 6 (f) --
our_map :: (a -> b) -> List a -> List b
our_map f (Empty) = Empty
our_map f (Cons x xs) = Cons (f x) (our_map f xs)


-- Question 7 --
data DTree a = Leaf a | Branch a (DTree a) (DTree a) deriving (Show, Read)

-- Question 7 (b) --
-- Branch 2 (Leaf 3) (Branch 1 (Leaf 9) (Leaf 5)) -- 

-- Question 7 (c) --
tree_mult :: DTree Int -> Int
tree_mult (Leaf a) = a
tree_mult (Branch x y z) = x * tree_mult y * tree_mult z 

-- Question 7 (d) --
sum_leafs :: DTree Int -> Int
sum_leafs (Leaf a) = a
sum_leafs (Branch x y z) = sum_leafs y + sum_leafs z

-- Question 7 (e) --
count_threes :: DTree Int -> Int
count_threes (Leaf a) = if a == 3 then 1 else 0
count_threes (Branch x y z) = if x == 3 then 1 + count_threes y + count_threes z else 0 + count_threes y + count_threes z 

-- Question 7 (f) --
get_leafs :: DTree Int -> [Int]
get_leafs (Leaf a) = [a]
get_leafs (Branch x y z) = get_leafs y ++ get_leafs z 