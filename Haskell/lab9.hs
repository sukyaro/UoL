import System.Environment
-- Question 3 --
-- Question 3 (c) --
echo :: IO ()
echo = do
    str <- getLine
    putStrLn str

-- Question 3 (d) --
double_echo :: IO()
double_echo = do
    x <- getLine
    putStrLn x
    putStrLn x

-- Question 3 (e) --
put_two_strs :: String -> String -> IO()
put_two_strs str1 str2 = do
    putStrLn str1
    putStrLn str2


-- Question 4 --
-- Question 4 (a) --
times_two :: IO()
times_two = do
    num <- getLine
    let number = read num :: Int
        number_1 = number * 2
    putStrLn $ show number_1

-- Question 4 (b) --
add :: IO()
add = do
    num_1 <- getLine
    num_2 <- getLine
    let number_1 = read num_1 :: Int
    let number_2 = read num_2 :: Int
    putStrLn $ show $ number_1 + number_2

-- Question 4 (c) --
io_reverse :: IO()
io_reverse = do
    line <- getLine
    putStrLn $ reverse line

-- Question 4 (d) --
guess_42 :: IO()
guess_42 = do
    number <-  getLine
    if number == "42" 
        then putStrLn "correct" 
        else putStrLn "wrong"


-- Question 5 --
get_int :: IO Int
get_int = do
    str <- getLine
    let n = read str :: Int
    return n

-- Question 5 (a) --
get_bool :: IO Bool
get_bool = do
    inp <- getLine
    let input = read inp :: Bool
    return input

-- Question 5 (b) --
get_two_and_add :: IO Int
get_two_and_add = do
    num_1 <- getLine
    num_2 <- getLine
    let number_1 = read num_1 :: Int
    let number_2 = read num_2 :: Int
    return $ number_1 + number_2

-- Question 5 (c) --
gt10 :: IO Bool
gt10 = do
    num <- getLine
    let number = read num :: Int
    if number > 10
        then return True
        else return False

-- Question 5 (d) --
get_two_strings :: IO (String, String)
get_two_strings = do
    line_1 <- getLine
    line_2 <- getLine
    return (line_1, line_2)


-- Question 6 --
echo_forever :: IO ()
echo_forever = do
    str <- getLine
    putStrLn str
    echo_forever

-- Question 6 (a) --
add_one_forever :: IO()
add_one_forever = do
    num <- getLine
    let number = read num :: Int
    putStrLn $ show $ number + 1
    add_one_forever

-- Question 6 (b) --
echo_until_quit :: IO()
echo_until_quit = do
    line <- getLine
    if line /= "quit"
        then 
            do
                putStrLn line
                echo_until_quit
        else return ()

-- Question 6 (c) --
print_numbers_between :: Int -> Int -> IO()
print_numbers_between num_1 num_2 = do
    if num_1 <= num_2
        then 
            do
                putStrLn $ show num_1
                print_numbers_between (num_1 + 1) num_2
        else
            return()


-- Question 7 --
-- Question 7 (a) --
print_file :: String -> IO()
print_file name = do
    file <- readFile name
    putStrLn file

-- Question 7 (b) --
first_line :: String -> IO()
first_line name = do
    file <- readFile name
    let file_list = lines file
    putStrLn $ file_list !! 0

-- Question 7 (c) --
get_lines :: String -> IO [String]
get_lines name = do
    file <- readFile name
    let file_list = lines file
    return file_list


-- Question 8 --
-- Question 8 (a) --
write_to :: String -> Int -> IO()
write_to name input = do
    let input_1 = show input
    writeFile name input_1

-- Question 8 (b) --
copy_file :: String -> String -> IO()
copy_file origin_file new_file = do
    file <- readFile origin_file
    writeFile new_file file

-- Question 8 (c) --
write_lines :: String -> [String] -> IO()
write_lines name list = do
    writeFile name $ unlines list


-- Question 9 (a) --
one_arg = do
    argument <- getArgs 
    putStrLn $ argument !! 0

-- Question 9 (b) --
sum_two = do
    num_1 <- getArgs
    num_2 <- getArgs
    let number_1 = read (num_1 !! 0) :: Int
    let number_2 = read (num_2 !! 0) :: Int
    putStrLn $ show $ number_1 + number_2

-- Question 9 (c) --
read_file_and_print = do
    file_derection <- getArgs
    file <- readFile $ file_derection !! 0
    putStrLn file

-- Question 9(d) --
copy = do
    file_dir_1 <- getArgs
    file_dir_2 <- getArgs
    file <- readFile $ file_dir_1 !! 0
    writeFile (file_dir_2 !! 1) file