-- Part A --
-- Question 1 --
char_to_int :: Char -> Integer
char_to_int char
    | char == '1' = 1
    | char == '2' = 2
    | char == '3' = 3
    | char == '4' = 4
    | char == '5' = 5
    | char == '6' = 6
    | char == '7' = 7
    | char == '8' = 8
    | char == '9' = 9
    | char == '0' = 0

-- Question 2 --
repeat_char :: Char -> Integer -> [Char]
repeat_char char 0 = []
repeat_char char number = char : repeat_char char (number - 1)

-- Question 3 --
decode :: [Char] -> [Char]
decode [] = []
decode (x:y:xs) = repeat_char x (char_to_int y) ++ decode xs


-- Part B --
-- Question 4 --
int_to_char :: Integer -> Char
int_to_char int
    | int == 1 = '1'
    | int == 2 = '2'
    | int == 3 = '3'
    | int == 4 = '4'
    | int == 5 = '5'
    | int == 6 = '6'
    | int == 7 = '7'
    | int == 8 = '8'
    | int == 9 = '9'
    | int == 0 = '0'

-- Question 5 --
length_char :: Char -> [Char] -> Integer
length_char char [] = 0
length_char char (x:xs)
    | x == char = 1 + length_char char xs
    | otherwise = length_char char []

-- Question 6 --
drop_char_help :: [Char] -> Integer -> [Char]
drop_char_help string 0 = string
drop_char_help string num = drop_char_help (tail string) (num - 1)

drop_char :: Char -> [Char] -> [Char]
drop_char char string = drop_char_help string (length_char char string)

-- Question 7 --
encode :: [Char] -> [Char]
encode [] = []
encode (x:xs) = x : [int_to_char $ length_char x xs + 1] ++ encode (drop_char x xs)


-- Part C --
-- Quesiton 8 --
are_same :: Char -> Char -> Bool
are_same char_1 char_2
    | char_1 == char_2 = True
    | otherwise        = False

int_to_char_pro :: Integer -> [Char]
int_to_char_pro num
    | num > 9   = int_to_char (div num 10) : [int_to_char $ mod num 10]
    | otherwise = [int_to_char num]

complex_encode_1 :: [Char] -> [Char]
complex_encode_1 (x:y:xs)
    | are_same x y = x : int_to_char_pro (length_char x (y:xs) + 1) ++ complex_encode (drop_char x (y:xs))
    | otherwise    = x : complex_encode (y:xs)

complex_encode :: [Char] -> [Char]
complex_encode [] = []
complex_encode (x:xs)
    | length (x:xs) > 1 = complex_encode_1 (x:xs)
    | otherwise         = [x]

-- Question 9 --
is_number :: Char -> Bool
is_number num 
    | num `elem` ['0'..'9'] = True
    | otherwise             = False

char_to_int_pro :: Char -> Char -> Integer
char_to_int_pro num_1 num_2 = char_to_int num_1 * 10 + char_to_int num_2

decode_pro :: Char -> Integer -> [Char]
decode_pro char 0 = []
decode_pro char num = char : decode_pro char (num - 1)

complex_decode_1 :: [Char] -> [Char]
complex_decode_1 (x:y:z:xs)
    | is_number z = decode_pro x (char_to_int_pro y z) ++ complex_decode xs
    | otherwise   = decode_pro x (char_to_int y) ++ complex_decode (z:xs)

complex_decode :: [Char] -> [Char]
complex_decode [] = []
complex_decode (x:y:xs)
    | length (x:y:xs) > 2  = complex_decode_1 (x:y:xs)
    | otherwise = decode_pro x (char_to_int y)
