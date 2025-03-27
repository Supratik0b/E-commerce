public class DNASequenceDecoder {

    public static void decodeSequence(String binarySequence) {
        // Check if the length of the binary sequence is valid (multiple of 3)
        if (binarySequence.length() % 3 != 0) {
            System.out.println("Invalid input: Length of binary sequence must be a multiple of 3");
            return;
        }

        // Determine if the sequence is DNA or RNA
        String typeIndicator = binarySequence.substring(0, 3);
        String sequence = binarySequence.substring(3);
        boolean isDNA;

        if ("000".equals(typeIndicator)) {
            isDNA = true;
        } else if ("111".equals(typeIndicator)) {
            isDNA = false;
        } else {
            System.out.println("Invalid input: Sequence must start with 000 (DNA) or 111 (RNA)");
            return;
        }

        // Decoded sequence
        StringBuilder decodedSequence = new StringBuilder();

        // Decode each 3-bit group
        for (int i = 0; i < sequence.length(); i += 3) {
            String triplet = sequence.substring(i, i + 3);
            if ("001".equals(triplet)) {
                decodedSequence.append("C");
            } else if ("010".equals(triplet)) {
                decodedSequence.append("G");
            } else if ("011".equals(triplet)) {
                decodedSequence.append("A");
            } else if ("101".equals(triplet)) {
                decodedSequence.append(isDNA ? "T" : "U");
            } else {
                // Handle unrecognized triplets (e.g., append a default value)
                decodedSequence.append(isDNA ? "T" : "U");
            }
        }

        // Correct invalid bases in DNA (replace 'U' with 'T')
        if (isDNA) {
            for (int i = 0; i < decodedSequence.length(); i++) {
                if (decodedSequence.charAt(i) == 'U') {
                    decodedSequence.setCharAt(i, 'T');
                }
            }
        }

        // Print the final decoded sequence
        System.out.println(decodedSequence.toString());
    }

    public static void main(String[] args) {
        // Example input
        String binarySequence = "000001001011101010010110011";
        decodeSequence(binarySequence); // Expected output: CCATGGTA
    }
}