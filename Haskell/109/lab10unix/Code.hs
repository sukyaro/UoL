-- Copy this code into Lab10.hs

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
