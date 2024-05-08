"""Drew Kroeger- CSC310- PROJECT 4
This is the vigenere cipher, it can encrypt and decrypt. It will take words from a file and write into another file. It really only works if the key is all letters
How it actually works is converts all letters to uppercase, and then will make then go  values,down to 1-25, also does that with the key, 
then will add a char value with a key value, and then bring that back up to 65-90 and then back to chars/string
decrpytion and encryption are almost the same, except for some addition versus subtraction signs, and checking if something is below 0 or in between 25 and 100 
punctuation is dealth with by being out of the values 65-90, and everything outside of that will just go up by 100, and then brought down when necessary
"""

def encrypt(file_obj, key):
    '''
    This function will take in a file object and a key, and will add them together to make an encrpyted (vignere cipher) file
    
    '''
    letter_list = []
    integer_list = []
    key_int_list = []
    encrypted_list = []
    key = key.upper()
    key_length = len(key)

    #this for loop will read from the file, and then get char by char, will put the ascii value of that char into a list
    for line in file_obj:                      
        line = line.upper()
        for element in line.split():
            for letter in element:
                letter_list.append(letter)
                integer_list.append(ord(letter))
    #print(integer_list ,"\n\n")



    #this for loop will make the values in the key put into a list, used later
    integer_list_length = len(integer_list)
    for i in key:
        key_int_list.append(ord(i))


    #the values in the key list will also be brought down to 0-25
    for element in range(0,len(key_int_list)):
        key_int_list[element] = key_int_list[element] -65



    #this for loop will bring down the values that we got from the file to 0-25, if something is outside of that is in the file(puntctuation) is will stay same ascii value, 1-26 are all commands, so we usually should not have to worry about overlapping 
    iteration = 0
    for element in integer_list:
        if element >= 65 and element <= 90:
            integer_list[iteration] = integer_list[iteration]-65
            iteration += 1
        else:
            iteration +=1



    #this is the actual encryption for loop , if the element is below 26(all regular letters) then we add it with the proper key index, we iterate over and over the key value with the mod key length function, if the value is punctuation we add 100 to it for next for loop
    index = 0
    for element in integer_list:
        #print("index:", index, "element:" , element,  "key_int_list:" , key_int_list[index % key_length] )
        if element < 26:
            encrypted_list.append(element + key_int_list[index % key_length])
            index +=1    
        else:
            encrypted_list.append(integer_list[index]+100)
            index+=1
            

    #print("FIRST TIME:",encrypted_list)



    #if the element is above 25, but below 100(punctuation is above 100), then the element is too high for a letter, so we bring it down by 26(mod 26) in order to assign a letter
    for i in range(len(encrypted_list)):
        if encrypted_list[i]> 25 and encrypted_list[i] < 100:
            encrypted_list[i] = encrypted_list[i] -26
        else:
            i +=1


    #print("Seconds Time:",encrypted_list)

    #we take the proper values and bring it back to the actual ascii chars we know and love
    for j in range(len(encrypted_list)):
        if encrypted_list[j] < 26:
            encrypted_list[j] = encrypted_list[j] + 65
            encrypted_list[j] = chr(encrypted_list[j])
        else:
            encrypted_list[j] = encrypted_list[j] - 100
            encrypted_list[j] = chr(encrypted_list[j])

    


    #print("Third time:",encrypted_list)



    #print("Key:" , key)
    #print(letter_list)
    #print(integer_list)
    #print(key)
    #print(key_int_list)
    #print(encrypted_list)
    return encrypted_list







#this is very similar to encrypt method, a few things are different
def decrypt(file_obj, key):
    letter_list = []
    integer_list = []
    key_int_list = []
    decrypted_list = []
    key = key.upper()
    key_length = len(key)

    
    for line in file_obj:
        line = line.upper()
        for element in line.split():
            for letter in element:
                letter_list.append(letter)
                integer_list.append(ord(letter))
    #print(integer_list ,"\n\n")




    integer_list_length = len(integer_list)
    for i in key:
        key_int_list.append(ord(i))
    for element in range(0,len(key_int_list)):
        key_int_list[element] = key_int_list[element] -65




    iteration = 0
    for element in integer_list:
        if element >= 65 and element <= 90:
            integer_list[iteration] = integer_list[iteration]-65
            iteration += 1
        else:
            iteration +=1

    #print(integer_list)

    #here if the element is below 26 we subtract, not add
    index = 0
    for element in integer_list:
        #print("index:", index, "element:" , element,  "key_int_list:" , key_int_list[index % key_length] )
        if element < 26:
            decrypted_list.append(element - key_int_list[index % key_length])
            index +=1    
        else:
            decrypted_list.append(integer_list[index]+100)
            index+=1
            

    #print("FIRST TIME:",decrypted_list)



    #here if the element is below zero we bring it up to a proper value, instead of subtraction above 26
    for i in range(len(decrypted_list)):
        if decrypted_list[i]< 0:
            decrypted_list[i] = decrypted_list[i] + 26
        else:
            i +=1


    #print("Seconds Time:",decrypted_list)

    
    for j in range(len(decrypted_list)):
        if decrypted_list[j] < 26:
            decrypted_list[j] = decrypted_list[j] + 65
            decrypted_list[j] = chr(decrypted_list[j])
        else:
            decrypted_list[j] = decrypted_list[j] - 100
            decrypted_list[j] = chr(decrypted_list[j])

    


    #print("Third time:",decrypted_list)



    #print("Key:" , key)
    #print(letter_list)
    #print(integer_list)
    #print(key)
    #print(key_int_list)
    #print(encrypted_list)
    return decrypted_list



#this just puts the string of data into a file
def output(content_str, file_name_str):
    output_file_object = open(file_name_str, 'w')
    print(content_str, file= output_file_object)
    output_file_object.close()


    return 1



def main():
    encryption_or_decryption = 0
    while encryption_or_decryption != 1 and encryption_or_decryption != 2:
        encryption_or_decryption = int(input("Do you want to encrypt or decrypt? 1 for encryption, 2 for decryption:"))


    input_checker =True
    while input_checker == True:
        file_str = input( "Enter name of the input file:" )       #need to make sure the first file is existing               
        try:
            file_object_one = open(file_str, 'r')
            input_checker = False
        except FileNotFoundError:
            print("The file", file_str ,"doesn't exist.")

    output_file_str = input("Enter the name of the output file:") #we can make this ourselves so it does not need to exist yet
    key = input("Enter the encryption key:")


    if encryption_or_decryption == 1:                             #decryption
        encryption_list = encrypt(file_object_one,key)
        encryption_string = ''.join(encryption_list)              #change this list to a string, so we can put it in the file easier
        #print(encryption_string)
        number = output(encryption_string, output_file_str)

    elif encryption_or_decryption == 2:                           # encryption
        decryption_list = decrypt(file_object_one,key)
        decryption_string = ''.join(decryption_list)              #again, list into string, makes it put into a file easier
        #print(decryption_string)
        number = output(decryption_string, output_file_str)



        file_object_one.close()                                   #close the file




main()                                                            #actually run the script
