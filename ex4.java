import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FaturamentoDistribuidora {
    public static void main(String[] args) {
        String filePath = "faturamento.json"; // Caminho para o arquivo JSON

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Ler todo o conteúdo do arquivo
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Converter o conteúdo para JSONArray
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Variáveis para cálculo
            double minRevenue = Double.MAX_VALUE;
            double maxRevenue = Double.MIN_VALUE;
            double totalRevenue = 0.0;
            int count = 0;

            // Processar cada objeto no JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double revenue = jsonObject.getDouble("revenue");

                // Atualizar o menor e o maior valor de faturamento
                if (revenue > 0) { // Ignorar dias sem faturamento
                    if (revenue < minRevenue) {
                        minRevenue = revenue;
                    }
                    if (revenue > maxRevenue) {
                        maxRevenue = revenue;
                    }
                    totalRevenue += revenue;
                    count++;
                }
            }

            // Calcular a média
            double averageRevenue = count > 0 ? totalRevenue / count : 0.0;

            // Contar o número de dias com faturamento superior à média
            int daysAboveAverage = 0;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double revenue = jsonObject.getDouble("revenue");

                if (revenue > averageRevenue) {
                    daysAboveAverage++;
                }
            }

            // Exibir os resultados
            System.out.println("Menor valor de faturamento: R$ " + minRevenue);
            System.out.println("Maior valor de faturamento: R$ " + maxRevenue);
            System.out.println("Número de dias com faturamento acima da média: " + daysAboveAverage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
