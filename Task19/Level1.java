public class Level1 {
    public static String[] ShopOLAP(int N, String[] items) {
        List<Product> oldProducts = new ArrayList<>();
        List<Product> newProducts = new ArrayList<>();
        for (String item : items) {
            String[] splitted = item.split(" ");
            oldProducts.add(new Product(splitted[0], Integer.parseInt(splitted[1])));
        }
        for (Product oldProduct : oldProducts) {
            int sum = calculateSumOfProduct(oldProduct.name, oldProducts);
            if (!containsProductWithName(oldProduct.name, newProducts)) {
                newProducts.add(new Product(oldProduct.name, sum));
            }
        }
        sortByCount(newProducts);
        sortByName(newProducts);
        return fromProductsListToStringArray(newProducts);
    }

    private static void sortByName(List<Product> products) {
        products.sort((o1, o2) -> {
            if (o2.count == o1.count) {
                return o1.name.compareTo(o2.name);
            }
            return 0;
        });
    }

    private static int calculateSumOfProduct(String productName, List<Product> products) {
        int sum = 0;
        for (Product product : products) {
            if (product.name.equals(productName)) {
                sum += product.count;
            }
        }
        return sum;
    }

    private static boolean containsProductWithName(String productName, List<Product> products) {
        for (Product product : products) {
            if (product.name.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    private static String[] fromProductsListToStringArray(List<Product> products) {
        String[] productsArray = new String[products.size()];
        for (int i = 0, productsSize = products.size(); i < productsSize; i++) {
            Product product = products.get(i);
            productsArray[i] = product.name + " " + product.count;
        }
        return productsArray;
    }

    private static void sortByCount(List<Product> products) {
        products.sort((o1, o2) -> o2.count - o1.count);
    }

    private static class Product {
        final String name;
        final int count;

        private Product(String name, int count) {
            this.name = name;
            this.count = count;
        }


    }
}
