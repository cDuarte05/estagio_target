import java.util.ArrayList;
import java.util.List;

public class ex3 {
    public static void main(String[] args) {
        // Simulação de um vetor com valores de faturamento diário
        List<Double> faturamentoDiario = new ArrayList<>();
        // Exemplo de dados, substitua pelos dados de seu JSON ou XML
        faturamentoDiario.add(100.0);
        faturamentoDiario.add(200.0);
        faturamentoDiario.add(0.0); // Fim de semana ou feriado
        faturamentoDiario.add(150.0);
        faturamentoDiario.add(300.0);
        // Adicione mais dados conforme necessário

        calcularFaturamento(faturamentoDiario);
    }

    public static void calcularFaturamento(List<Double> faturamentoDiario) {
        double menorFaturamento = Double.MAX_VALUE;
        double maiorFaturamento = Double.MIN_VALUE;
        double somaFaturamento = 0;
        int diasComFaturamento = 0;

        for (double faturamento : faturamentoDiario) {
            if (faturamento > 0) {
                if (faturamento < menorFaturamento) menorFaturamento = faturamento;
                if (faturamento > maiorFaturamento) maiorFaturamento = faturamento;
                somaFaturamento += faturamento;
                diasComFaturamento++;
            }
        }

        double mediaMensal = somaFaturamento / diasComFaturamento;
        int diasAcimaMedia = 0;

        for (double faturamento : faturamentoDiario) {
            if (faturamento > mediaMensal) diasAcimaMedia++;
        }

        System.out.println("Menor faturamento: " + menorFaturamento);
        System.out.println("Maior faturamento: " + maiorFaturamento);
        System.out.println("Dias acima da média: " + diasAcimaMedia);
    }
}
