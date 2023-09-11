# Syllabus APC

## Prerequisites

Before you begin, make sure you have OpenSSL installed on your system. You can typically install OpenSSL via your
system's package manager.

## Step 1: Generate an RSA Key Pair

Run the following command to generate a 2048-bit RSA private key and save it to a file named `keypair.pem`:

```bash
openssl genrsa -out keypair.pem 2048
```

## Step 2: Generate a Corresponding Public Key

Use the following command to generate a corresponding public key from the private key and save it to a file named
public.pem:

```bash
openssl rsa -in keypair.pem -pubout -out public.pem
```

## Step 3: Convert the Private Key to PKCS#8 Format

To convert the private key from PEM format to PKCS#8 format and save it to a file named private.pem without encryption,
run the following command:

```bash
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```
