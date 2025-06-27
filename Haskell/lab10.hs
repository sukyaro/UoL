{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
get :: [String] -> Int -> Int -> Char
get maze x y = (maze !! y) !! x

modify_list :: [a] -> Int -> a -> [a]
modify_list list pos new =
    let
        before = take  pos    list
        after  = drop (pos+1) list
    in
        before ++ [new] ++ after

set :: [String] -> Int -> Int -> Char -> [String]
set maze x y char =
    let
        line = maze !! y
        new_line = modify_list line x char
        new_maze = modify_list maze y new_line
    in
        new_maze

maze_path = "/Users/sukayarik/Desktop/Haskell/lab10unix/maze2.txt"

-- Part A --
-- Question 1 --
get_maze :: String -> IO [String]
get_maze name = do
    file <- readFile name
    return $ lines file

-- Question 2 --
print_maze :: [String] -> IO()
print_maze maze = do
    putStrLn $ unlines maze

-- Question 3 --
is_wall :: [String] -> (Int, Int) -> Bool
is_wall maze (x, y) = do
    get maze x y == '#'

-- Question 4 --
place_object :: [String] -> (Int, Int) -> Char -> [String]
place_object maze (x, y) symb = do
    set maze x y symb

-- Question 5 --
move :: (Int, Int) -> Char -> (Int, Int)
move (x, y) symb
    | symb == 'w' = (x, y-1)
    | symb == 's' = (x, y+1)
    | symb == 'a' = (x-1, y)
    | symb == 'd' = (x+1, y)
    | otherwise   = (x, y)

-- Question 6 --
can_move :: [String] -> (Int, Int) -> Char -> Bool
can_move maze (x, y) symb = do
    not (is_wall maze  (move (x, y) symb))

-- Question 7 --
game_loop :: [String] -> (Int, Int) -> (Int, Int) -> IO()
game_loop maze (a, b) (c, d) = do
    let maz = place_object maze (a, b) '@'
    let new_maz = place_object maz (c, d) '>'
    print_maze new_maz
    input <- getLine
    if input == "quit"
        then
            return ()
        else do
            let inp = head input
            if can_move new_maz (a, b) inp
                then
                    if move (a, b) inp == (c, d)
                        then
                            putStrLn "You win!"
                        else
                            game_loop maze (move (a, b) inp) (c, d)
                        else
                            game_loop maze (a, b) (c, d)