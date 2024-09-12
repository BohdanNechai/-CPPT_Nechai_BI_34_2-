package KI304.Nechai.Lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.System.out;

//y=tg(x)/(sin(4x) – 2cos(x)) це мій вираз

public class CalculateExpression {
    public static void main(String[] args) throws FileNotFoundException {
        try
        {

            Scanner in = new Scanner(System.in);
            PrintWriter fout = new PrintWriter(new File("result"));
            try
            {
                try
                {
                    Equations eq = new Equations();
                    out.print("Enter X: ");
                    int input = in.nextInt();
                    fout.print(eq.calculate(input));
                    out.print(eq.calculate(input));
                }
                finally
                {
// Цей блок виконається за будь-яких обставин
                    fout.flush();
                    fout.close();
                }
            }
            catch (CalcException ex)
            {
// Блок перехоплює помилки обчислень виразу
                out.print(ex.getMessage());
            }
        }
        catch (FileNotFoundException ex)
        {
// Блок перехоплює помилки роботи з файлом навіть якщо вони // виникли у блоці finally
            out.print("Exception reason: Perhaps wrong file path");
        }
    }
}

    class CalcException extends ArithmeticException
    {
        public CalcException(){}
        public CalcException(String cause)
        {
            super(cause);
        }
    }
class Equations{
        public double calculate(int x) throws CalcException{
            double y,rad;
            rad = x * Math.PI / 180.0;
            try
            {
                y = (Math.tan(rad)/(Math.sin(4*x)) - 2*Math.cos(x));
                if (y==Double.NaN || y==Double.NEGATIVE_INFINITY || y==Double.POSITIVE_INFINITY || x==90 || x== -90)
                    throw new ArithmeticException();
            }
            catch (ArithmeticException ex)
            {
// створимо виключення вищого рівня з поясненням причини // виникнення помилки
                if (rad==Math.PI/2.0 || rad==-Math.PI/2.0)
                    throw new CalcException("Exception reason: Illegal value of X for tangent calculation");
                else if (x==0)
                    throw new CalcException("Exception reason: X = 0");
                else
                    throw new CalcException("Unknown reason of the exception during exception calculation");
            }
            return y;
        }
        }

