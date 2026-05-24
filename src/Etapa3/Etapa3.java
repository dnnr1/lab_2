package Etapa3;

public class Etapa3 {
    public boolean checkBrackets(Stack<Character> s1) {
        if (s1 == null || s1.isEmpty()) {
            return true;
        }
        StaticStack<Character> temp = new StaticStack<>(s1.numElements());
        while (!s1.isEmpty()) {
            temp.push(s1.pop());
        }
        int balance = 0;
        while (!temp.isEmpty()) {
            char ch = temp.pop();
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
                if (balance < 0) {
                    return false;
                }
            }
        }
        return balance == 0;
    }
}
