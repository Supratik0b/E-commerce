def decode_sequence(binary_sequence):
    # Check if the length of the binary sequence is valid (multiple of 3)
    if len(binary_sequence) % 3 != 0:
        print("Invalid input: Length of binary sequence must be a multiple of 3")
        return

    # Determine if the sequence is DNA or RNA
    type_indicator = binary_sequence[:3]
    sequence = binary_sequence[3:]
    is_dna = None

    if type_indicator == "000":
        is_dna = True
    elif type_indicator == "111":
        is_dna = False
    else:
        print("Invalid input: Sequence must start with 000 (DNA) or 111 (RNA)")
        return

    # Decoded sequence
    decoded_sequence = []

    # Decode each 3-bit group
    for i in range(0, len(sequence), 3):
        triplet = sequence[i:i + 3]
        if triplet == "001":
            decoded_sequence.append("C")
        elif triplet == "010":
            decoded_sequence.append("G")
        elif triplet == "011":
            decoded_sequence.append("A")
        elif triplet == "101":
            decoded_sequence.append("T" if is_dna else "U")
        else:
            # Handle unrecognized triplets
            decoded_sequence.append("T" if is_dna else "U")

    # Correct invalid bases in DNA (replace 'U' with 'T')
    if is_dna:
        decoded_sequence = ["T" if base == "U" else base for base in decoded_sequence]

    # Print the final decoded sequence
    print("".join(decoded_sequence))
