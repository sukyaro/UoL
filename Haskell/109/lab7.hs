-- For Parts A and B --

type Transaction = (Char, Int, Int, String, Int) 

test_log :: [Transaction]
test_log = [('B', 100, 1104,  "VTI",  1),
            ('B', 200,   36, "ONEQ",  3),
            ('B',  50, 1223,  "VTI",  5),
            ('S', 150, 1240,  "VTI",  9),
            ('B', 100,  229, "IWRD", 10),
            ('S', 200,   32, "ONEQ", 11), 
            ('S', 100,  210, "IWRD", 12)
            ]

-- Part A --
-- Question 1 --
transaction_to_string :: Transaction -> String
transaction_to_string (b_s, uni, pri, ty, d) = 
    let
        buy_sell = if b_s == 'B' then "Bought" else "Sold"
        units = show uni
        price = show pri
        type_1 = ty
        day = show d
    in
        buy_sell ++ " " ++ units ++ " of " ++ type_1 ++ " for " ++ price ++ " pounds each on day " ++ day

-- Question 2 --
trade_report_list :: [Transaction] -> [String]
trade_report_list = map (transaction_to_string)

-- Question 3 --
stock_test :: String -> Transaction -> Bool
stock_test name (_, _, _, x, _)
    | name == x = True
    | otherwise = False

-- Question 4 --
get_trades :: String -> [Transaction] -> [Transaction]
get_trades name stock = filter (stock_test name) stock

-- Question 5 --
trade_report :: String -> [Transaction] -> String
trade_report name stock = unlines $ trade_report_list $ get_trades name stock


-- Part B --
-- Question 6 --
update_money :: Transaction -> Int -> Int
update_money (b_s, units, price, _, _) money_got
    | b_s == 'B' = money_got - units * price
    | otherwise  = money_got + units * price

-- Question 7 --
profit :: [Transaction] -> String -> Int
profit stock name = foldr (\x acc -> update_money x acc) 0 $ get_trades name stock

-- Question 8 --
profit_report :: [String] -> [Transaction] -> String
profit_report list stock = unlines $ foldr (\x acc -> (x ++ ": " ++ show (profit stock x)) : acc) [] list


-- Part C --
test_str_log = "BUY 100 VTI 1\nBUY 200 ONEQ 3\nBUY 50 VTI 5\nSELL 150 VTI 9\nBUY 100 IWRD 10\nSELL 200 ONEQ 11\nSELL 100 IWRD 12\n"

type Prices = [(String, [Int])]

test_prices :: Prices
test_prices = [
                ("VTI", [1689, 1785, 177, 1765, 1739, 1725, 1615, 1683, 1655, 1725, 1703, 1726, 1725, 1742, 1707, 1688, 1697, 1688, 1675]),
                ("ONEQ", [201, 203, 199, 199, 193, 189, 189, 183, 185, 190, 186, 182, 186, 182, 182, 186, 183, 179, 178]),
                ("IWRD", [207, 211, 213, 221, 221, 222, 221, 218, 226, 234, 229, 229, 228, 222, 218, 223, 222, 218, 214])
              ]

list_test_str :: String -> [[String]]
list_test_str stock = map (words) $ lines stock

get_the_price name day prices 3 = error "there is no such a stock"
get_the_price name day prices count
    | fst (prices !! count) == name = (snd (prices !! count)) !! day
    | otherwise                     = get_the_price name day prices (count + 1)

money_update [b_s, units, name, day] money prices
    | b_s == "BUY" = money - (read units :: Int) * get_the_price name ((read day :: Int) - 1) prices 0
    | otherwise    = money + (read units :: Int) * get_the_price name ((read day :: Int) - 1) prices 0

profit_lose stock name prices = foldr (\[a, b, c, d] acc -> if c == name then money_update [a, b, c, d] acc prices else acc) 0 $ list_test_str stock

complex_profit_report stock prices = unlines $ foldr (\x acc -> (fst(x) ++ ": " ++ (show $ profit_lose stock (fst x) prices)) : acc) [] prices